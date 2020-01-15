package com.linzd.unifiedauth.login.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.unifiedauth.login.entity.User;
import com.linzd.unifiedauth.login.mapper.UserMapper;
import com.linzd.unifiedauth.login.service.UserService;
import com.linzd.unifiedauth.utils.Encrypt;
import com.linzd.unifiedauth.utils.ResultUtil;
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

    /**
     * 描述  登录
     *
     * @author Lorenzo Lin
     * @params  账号  密码
     * @created 2020/1/15 11:53
     **/
    @Override
    public ResultUtil login(String name, String password) {
        String md5Password = Encrypt.md5AndSha(password);
        User user_condition=new User();
        user_condition.setLoginname(name);
        user_condition.setPassword(md5Password);
//        User user = userMapper.selectOne(user_condition);
        return null;
    }
}
