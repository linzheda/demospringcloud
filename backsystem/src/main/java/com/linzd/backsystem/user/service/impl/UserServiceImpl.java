package com.linzd.backsystem.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.user.entity.User;
import com.linzd.backsystem.user.mapper.UserMapper;
import com.linzd.backsystem.user.service.UserService;
import com.linzd.backsystem.utils.Encrypt;
import com.linzd.backsystem.utils.JwtTokenUtil;
import com.linzd.backsystem.utils.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-01-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 描述  登录
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/1/15 20:53
     **/
    @Override
    public ResultUtil login(String name, String password) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            return ResultUtil.error("用户名或密码不允许为空");
        }
        String md5Password = Encrypt.md5AndSha(password);
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("loginname",name);
        queryWrapper.eq("password",md5Password);
        User user = null;
        try {
            user = userMapper.selectOne(queryWrapper);
            String token = JwtTokenUtil.sign(user.getId());
            user.setToken(token);
        } catch (Exception e) {
            user = null;
            return ResultUtil.error(e.toString());
        }
        if (user != null) {
            return ResultUtil.success(user);
        } else {
            return ResultUtil.error("用户名或密码错误..");
        }

    }
}
