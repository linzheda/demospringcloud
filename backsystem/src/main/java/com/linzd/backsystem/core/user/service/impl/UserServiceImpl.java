package com.linzd.backsystem.core.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.backsystem.core.pub.service.PubService;
import com.linzd.backsystem.core.sysparam.entity.SysParam;
import com.linzd.backsystem.core.user.entity.User;
import com.linzd.backsystem.core.user.mapper.UserMapper;
import com.linzd.backsystem.core.user.service.UserService;
import com.linzd.basecore.utils.EncryptUtil;
import com.linzd.basecore.utils.JwtTokenUtil;
import com.linzd.basecore.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-01-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private PubService pubService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 描述  登录
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/1/15 20:53
     **/
    @Override
    public ResultPojo login(String name, String password) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            return ResultPojo.error("用户名或密码不允许为空");
        }
        String md5Password = password;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginname", name);
        //查询超级密码
        SysParam superPassword = pubService.getSysParam("superPassword");
        String superPasswordText = superPassword.getValue();
        if (!md5Password.equals(EncryptUtil.md5(superPasswordText))) {
            //说明不是超级密码
            queryWrapper.eq("password", md5Password);
        }
        User user = mapper.selectOne(queryWrapper);
        if (user != null) {
            if (user.getStatus() == 1) {
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
                return ResultPojo.success(user);
            } else {
                return ResultPojo.error("账户被冻结,请联系管理员..");
            }
        } else {
            return ResultPojo.error("用户名或密码错误..");
        }

    }

    /**
     * 描述  修改密码
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/4/10 17:02
     **/
    @Override
    public ResultPojo updatePassword(Long id, String oldPassword, String newPassword) {
        User user = mapper.selectById(id);
        String oldPwdMd5 = EncryptUtil.md5(oldPassword);
        boolean isSuccess = false;
        String msg = "";
        if (!user.getPassword().equals(oldPwdMd5)) {
            isSuccess = false;
            msg = "原密码错误";
        } else {
            isSuccess = true;
            String newPwdMd5 = EncryptUtil.md5(newPassword);
            user.setPassword(newPwdMd5);
            user.updateById();
            isSuccess = true;
            msg = "修改密码成功,请重新登录";
        }

        return ResultPojo.success(msg, isSuccess);
    }

    /**
     * 描述  登出
     *
     * @param id
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/21 16:08
     */
    @Override
    public ResultPojo loginOut(Long id) {
        String key = JwtTokenUtil.LOGIN_TOKEN_PREFIX + id;
        if (redisUtil.hasKey(key)) {
            redisUtil.del(key);
        }
        return ResultPojo.success("登出成功");
    }

    /**
     * 描述  获取用户列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/25 18:08
     **/
    @Override
    public ResultPojo getUserList(Map<String, Object> condition) {
        long current = Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current, size);
        IPage<Map> result = mapper.getUserList(page, condition);
        return ResultPojo.success(result);
    }

    /**
     * 描述  获取在线用户列表
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/21 16:56
     */
    @Override
    public ResultPojo getOnlineUserList(Map<String, Object> condition) {
        List<Object> onlineUserList = redisUtil.getByKeyPrefix(JwtTokenUtil.LOGIN_TOKEN_PREFIX);
        List<Long> userids = new ArrayList<>();
        for(Object token:onlineUserList){
            userids.add(JwtTokenUtil.getUserIdByToken((String)token));
        }
        condition.put("userids", StringUtils.join(userids, ","));
        long current = Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current, size);
        IPage<Map> result = mapper.getUserList(page, condition);
        return ResultPojo.success(result);
    }




}
