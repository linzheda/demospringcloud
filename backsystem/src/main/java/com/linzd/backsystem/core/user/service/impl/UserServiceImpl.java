package com.linzd.backsystem.core.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.core.sysparam.entity.SysParam;
import com.linzd.backsystem.core.user.entity.User;
import com.linzd.backsystem.core.user.mapper.UserMapper;
import com.linzd.backsystem.core.user.service.UserService;
import com.linzd.backsystem.utils.EncryptUtil;
import com.linzd.backsystem.utils.JwtTokenUtil;
import com.linzd.backsystem.common.entity.ResultPojo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        QueryWrapper<SysParam> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("code", "superPassword");
        SysParam superPassword=new SysParam().selectOne(queryWrapper2);
        String superPasswordText = superPassword.getValue();
        if(!md5Password.equals(EncryptUtil.md5(superPasswordText))){
            //说明不是超级密码
            queryWrapper.eq("password", md5Password);
        }
        User user = null;
        try {
            user = mapper.selectOne(queryWrapper);
            String token = JwtTokenUtil.sign(user.getId());
            user.setToken(token);
        } catch (Exception e) {
            user = null;
        }
        if (user != null) {
            if(user.getStatus()==1){
                return ResultPojo.success(user);
            }else{
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
        boolean isSuccess=false;
        String msg="";
        if (!user.getPassword().equals(oldPwdMd5)) {
            isSuccess=false;
            msg="原密码错误";
        }else{
            isSuccess=true;
            String newPwdMd5 = EncryptUtil.md5(newPassword);
            user.setPassword(newPwdMd5);
            user.updateById();
            isSuccess=true;
            msg="修改密码成功,请重新登录";
        }

        return ResultPojo.success(msg, isSuccess);
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
        long current= Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current,size);
        IPage<Map> result=mapper.getUserList(page,condition);
        return ResultPojo.success(result);
    }
}
