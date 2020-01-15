package com.linzd.unifiedauth.login.service.impl;

import com.linzd.unifiedauth.login.entity.User;
import com.linzd.unifiedauth.login.mapper.UserMapper;
import com.linzd.unifiedauth.login.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
