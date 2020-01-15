package com.linzd.login.login.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.login.login.entity.User;
import com.linzd.login.login.mapper.UserMapper;
import com.linzd.login.login.service.UserService;
import com.linzd.login.utils.Encrypt;
import com.linzd.login.utils.ResultUtil;
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


}
