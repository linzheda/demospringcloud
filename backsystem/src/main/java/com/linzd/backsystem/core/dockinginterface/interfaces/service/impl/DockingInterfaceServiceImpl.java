package com.linzd.backsystem.core.dockinginterface.interfaces.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.backsystem.core.docking.entity.ThirdPartyDocking;
import com.linzd.backsystem.core.dockinginterface.interfaces.mapper.DockingInterfaceMapper;
import com.linzd.backsystem.core.dockinginterface.interfaces.service.DockingInterfaceService;
import com.linzd.backsystem.core.dockinginterface.manager.entity.DockingInterface;
import com.linzd.backsystem.core.pub.service.PubService;
import com.linzd.backsystem.core.sysparam.entity.SysParam;
import com.linzd.backsystem.core.user.entity.*;
import com.linzd.basecore.utils.EncryptUtil;
import com.linzd.basecore.utils.JwtTokenUtil;
import com.linzd.basecore.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 对外接口表 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-08-20
 */
@Service
public class DockingInterfaceServiceImpl extends ServiceImpl<DockingInterfaceMapper, DockingInterface> implements DockingInterfaceService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private DockingInterfaceMapper mapper;

    @Autowired
    private PubService pubService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 描述  获取访问令牌
     *
     * @param
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 10:39
     */
    @Override
    public ResultPojo getToken(String name, String password) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            return ResultPojo.error("用户名或密码不允许为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginname", name);
        queryWrapper.eq("password", password);
        User user = null;
        try {
            user = new User().selectOne(queryWrapper);
            //查询登录策略
            SysParam loginType = pubService.getSysParam("loginType");
            String token = null;
            String key = JwtTokenUtil.LOGIN_TOKEN_PREFIX + user.getId();
            if (loginType.getValue().equals("1")) {
                //允许同时在线
                if (redisUtil.hasKey(key)) {
                    //如果键存在则取出 token
                    token = redisUtil.get(key).toString();
                } else {
                    //如果不存在则生成新的token
                    token = JwtTokenUtil.sign(user.getId());
                    redisUtil.set(key, token, JwtTokenUtil.EXPIRE_TIME, TimeUnit.MILLISECONDS);
                }
            } else {
                //不允许同时在线生成新的token
                token = JwtTokenUtil.sign(user.getId());
                redisUtil.set(key, token, JwtTokenUtil.EXPIRE_TIME,TimeUnit.MILLISECONDS);
            }
            user.setToken(token);
        } catch (Exception e) {
            user = null;
        }
        if (user != null) {
            Gson gson=new Gson();
            String json=gson.toJson(user);
            Map<String,Object> verifyResult=JwtTokenUtil.verify(user.getToken());
            Map<String,Object>result = gson.fromJson(json, Map.class);
            result.put("tokenExpTime",verifyResult.get("exp"));
            return ResultPojo.success(result);
        } else {
            return ResultPojo.error("用户名或密码错误..");
        }
    }

    /**
     * 描述  根据token获取用户信息
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 11:38
     **/
    @Override
    public ResultPojo getUserInfoByToken() {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        String token = request.getHeader("Authorization");
        Map<String, Object> verifyResult = JwtTokenUtil.verify(token);
        Long userId = (long) verifyResult.get("userId");
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("id", userId);
        User user = new User().selectOne(queryWrapper);
        return ResultPojo.success(user);
    }

    /**
     * 描述  获取用户列表
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 11:42
     */
    @Override
    public ResultPojo getUserList(Map<String, Object> condition) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        Gson g = new Gson();
        Map obj = g.fromJson(tpd.getAttr(), Map.class);
        condition.put("tag", obj.get("user_tag"));
        condition.put("resources_isn", obj.get("resources_isn"));
        long current = Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current, size);
        IPage<Map> result = mapper.getUserList(page, condition);
        return ResultPojo.success(result);
    }

    /**
     * 描述  编辑用户
     *
     * @param user
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 14:40
     */
    @Override
    public ResultPojo editUser(User user) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        boolean isInsert = user.getId() != null ? false : true;
        String msg = isInsert ? "新增" : "编辑";
        if (isInsert && user.getPassword() == null) {
            //设置默认密码
            QueryWrapper<SysParam> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("code", "password");
            SysParam defaultPassword = new SysParam().selectOne(queryWrapper);
            String md5Password = EncryptUtil.md5(defaultPassword.getValue());
            user.setPassword(md5Password);
        }
        Gson g = new Gson();
        Map obj = g.fromJson(tpd.getAttr(), Map.class);
        if (user.getTag() == null || !user.getTag().contains(obj.get("user_tag").toString())) {
            String tag = (user.getTag() == null || user.getTag().isEmpty()) ? obj.get("user_tag").toString() : "," + obj.get("user_tag");
            user.setTag(tag);
        }
        user.setUpdateby(null);
        user.setUpdatetime(null);
        boolean isSuccess = user.insertOrUpdate();
        msg += isSuccess ? "成功" : "失败";
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("id", user.getId());
        return ResultPojo.success(msg, result);
    }

    /**
     * 描述  获取组织机构列表
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 15:09
     */
    @Override
    public ResultPojo getOrganizationList(Map<String, Object> condition) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        Gson g = new Gson();
        Map obj = g.fromJson(tpd.getAttr(), Map.class);
        condition.put("isn", obj.get("org_isn"));
        long current = Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current, size);
        IPage<Map> result = mapper.getOrganizationList(page, condition);
        return ResultPojo.success(result);
    }

    /**
     * 描述  编辑组织机构
     *
     * @param organization
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 15:40
     */
    @Override
    public ResultPojo editOrganization(Organization organization) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        Gson g = new Gson();
        Map obj = g.fromJson(tpd.getAttr(), Map.class);

        String msg = organization.getId() != null ? "编辑" : "新增";
        organization.setUpdatetime(null);
        organization.setUpdateby(null);
        boolean isSuccess = organization.insertOrUpdate();
        msg += isSuccess ? "成功" : "失败";
        if (organization.getPid() == null || organization.getPid() == 0) {
            //说明插入根节点 (应用级组织机构下)
            QueryWrapper<Organization> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("isn", obj.get("org_isn"));
            Organization p = new Organization().selectOne(queryWrapper);
            if (!p.getIsn().contains(obj.get("org_isn").toString())) {
                //如果不存在
                return ResultPojo.error("请确认组织机构信息的父级id是否正确");
            }
            organization.setIsn(p.getIsn() + "." + organization.getId());
            organization.setLevel(p.getLevel() + 1);
            organization.setPid(p.getId());
        } else {
            //说明有父级
            Organization p = new Organization().selectById(organization.getPid());

            if (!p.getIsn().contains(obj.get("org_isn").toString())) {
                //如果不存在
                return ResultPojo.error("请确认组织机构信息的父级id是否正确");
            }
            organization.setIsn(p.getIsn() + "." + organization.getId());
            organization.setLevel(p.getLevel() + 1);
        }
        if(organization.getStatus()==null){
            organization.setStatus(1);
        }
        organization.updateById();
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("id", organization.getId());
        return ResultPojo.success(msg, result);
    }

    /**
     * 描述  获取菜单列表
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 15:57
     */
    @Override
    public ResultPojo getResourcesList(Map<String, Object> condition) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        Gson g = new Gson();
        Map obj = g.fromJson(tpd.getAttr(), Map.class);
        condition.put("isn", obj.get("resources_isn"));
        long current = Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current, size);
        IPage<Map> result = mapper.getResourcesList(page, condition);
        return ResultPojo.success(result);
    }

    /**
     * 描述  编辑菜单
     *
     * @param resources
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 16:17
     */
    @Override
    public ResultPojo editResources(Resources resources) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        Gson g = new Gson();
        Map obj = g.fromJson(tpd.getAttr(), Map.class);

        String msg = resources.getId() != null ? "编辑" : "新增";
        resources.setUpdatetime(null);
        resources.setUpdateby(null);
        boolean isSuccess = resources.insertOrUpdate();
        msg += isSuccess ? "成功" : "失败";
        if (resources.getPid() == null || resources.getPid() == 0) {
            //说明插入根节点 (应用级组织机构下)
            QueryWrapper<Resources> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("isn", obj.get("resources_isn"));
            Resources p = new Resources().selectOne(queryWrapper);
            if (!p.getIsn().contains(obj.get("resources_isn").toString())) {
                //如果不存在
                return ResultPojo.error("请确认菜单信息的父级id是否正确");
            }
            resources.setIsn(p.getIsn() + "." + resources.getId());
            resources.setLevel(p.getLevel() + 1);
            resources.setPid(p.getId());
        } else {
            //说明有父级
            Resources p = new Resources().selectById(resources.getPid());
            if (!p.getIsn().contains(obj.get("resources_isn").toString())) {
                //如果不存在
                return ResultPojo.error("请确认菜单信息的父级id是否正确");
            }
            resources.setIsn(p.getIsn() + "." + resources.getId());
            resources.setLevel(p.getLevel() + 1);
        }
        resources.updateById();
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("id", resources.getId());
        return ResultPojo.success(msg, result);
    }

    /**
     * 描述  获取角色列表
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 16:24
     */
    @Override
    public ResultPojo getRoleList(Map<String, Object> condition) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        Gson g = new Gson();
        Map obj = g.fromJson(tpd.getAttr(), Map.class);
        condition.put("isn", obj.get("resources_isn"));
        long current = Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current, size);
        IPage<Map> result = mapper.getRoleList(page, condition);
        return ResultPojo.success(result);
    }

    /**
     * 描述  编辑角色
     *
     * @param role
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 16:44
     */
    @Override
    public ResultPojo editRole(Role role) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        Gson g = new Gson();
        Map obj = g.fromJson(tpd.getAttr(), Map.class);
        boolean isInsert = role.getId() != null ? false : true;
        String msg = isInsert ? "新增" : "编辑";
        if (role.getTag() == null || !role.getTag().contains(obj.get("user_tag").toString())) {
            String tag = (role.getTag() == null || role.getTag().isEmpty()) ? obj.get("user_tag").toString() : "," + obj.get("user_tag");
            role.setTag(tag);
        }
        role.setUpdatetime(null);
        role.setUpdateby(null);
        boolean isSuccess = role.insertOrUpdate();
        msg += isSuccess ? "成功" : "失败";
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("id", role.getId());
        return ResultPojo.success(msg, result);
    }

    /**
     * 描述  获取资源列表根据角色id
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 16:47
     */
    @Override
    public ResultPojo getResourcesListByRoleId(Map<String, Object> condition) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        Gson g = new Gson();
        Map obj = g.fromJson(tpd.getAttr(), Map.class);
        condition.put("isn", obj.get("resources_isn"));
        List<Map<String, Object>> result = mapper.getResourcesListByRoleId(condition);
        return ResultPojo.success(result);
    }

    /**
     * 描述  分配角色资源列表
     *
     * @param roleid
     * @param addArr
     * @param delArr
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 16:56
     */
    @Override
    public ResultPojo updateRoleResourcesByRoleId(Long roleid, List<Long> addArr, List<Long> delArr) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        //删除
        if (!delArr.isEmpty()) {
            QueryWrapper<RoleResources> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("roleid", roleid);
            queryWrapper.in("resid", delArr);
            new RoleResources().delete(queryWrapper);
        }
        //新增
        if (!addArr.isEmpty()) {
            for (Long resid : addArr) {
                RoleResources rr = new RoleResources();
                rr.setRoleid(roleid);
                rr.setResid(resid);
                rr.insert();
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", true);
        return ResultPojo.success("资源菜单分配成功", result);
    }

    /**
     * 描述  获取这个角色下的用户列表
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 17:01
     */
    @Override
    public ResultPojo getUserListByRoleId(Map<String, Object> condition) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        Gson g = new Gson();
        Map obj = g.fromJson(tpd.getAttr(), Map.class);
        condition.put("tag", obj.get("user_tag"));
        List<Map> result = mapper.getUserListByRoleId(condition);
        return ResultPojo.success(result);
    }

    /**
     * 描述  更新用户角色表
     *
     * @param roleid
     * @param addArr
     * @param delArr
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 17:04
     */
    @Override
    public ResultPojo updateRoleUserByRoleId(Long roleid, List<Long> addArr, List<Long> delArr) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        //删除
        if (!delArr.isEmpty()) {
            QueryWrapper<RoleUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("roleid", roleid);
            queryWrapper.in("userid", delArr);
            new RoleUser().delete(queryWrapper);
        }
        //新增
        if (!addArr.isEmpty()) {
            for (Long userid : addArr) {
                RoleUser ru = new RoleUser();
                ru.setRoleid(roleid);
                ru.setUserid(userid);
                ru.insert();
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", true);
        return ResultPojo.success("角色用户分配成功", result);
    }

    /**
     * 描述  获取这个用户下的角色列表(全部)
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/3 15:01
     */
    @Override
    public ResultPojo getRoleListByUserId(Map<String, Object> condition) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        List<Map> result = mapper.getRoleListByUserId(condition);
        return ResultPojo.success(result);
    }

    /**
     * 描述  修改RoleUser表通过用户id
     *
     * @param userid
     * @param addArr
     * @param delArr
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/3 15:18
     */
    @Override
    public ResultPojo updateRoleUserByUserId(Long userid, List<Long> addArr, List<Long> delArr) {
        ThirdPartyDocking tpd = checkClient();
        if (tpd == null) {
            return ResultPojo.error("秘钥或标识错误");
        }
        //删除
        if(!delArr.isEmpty()){
            QueryWrapper<RoleUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userid",userid);
            queryWrapper.in("roleid",delArr);
            new RoleUser().delete(queryWrapper);
        }
        //新增
        if(!addArr.isEmpty()){
            for(Long roleid:addArr){
                RoleUser ru=new RoleUser();
                ru.setRoleid(roleid);
                ru.setUserid(userid);
                ru.insert();
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", true);
        return ResultPojo.success("用户角色分配成功",result);
    }


    /**
     * 描述  检查id和秘钥是否正确
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 10:51
     **/
    public ThirdPartyDocking checkClient() {
        String id = request.getHeader("client_id");
        String secret = request.getHeader("client_secret");
        QueryWrapper<ThirdPartyDocking> qw = new QueryWrapper<>();
        qw.eq("client_id", id);
        qw.eq("client_secret", secret);
        ThirdPartyDocking tpd = new ThirdPartyDocking().selectOne(qw);
        return tpd;
    }


}
