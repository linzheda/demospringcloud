package com.linzd.unifiedauth.login.service;

import com.linzd.unifiedauth.login.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.unifiedauth.utils.ResultUtil;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linzd
 * @since 2020-01-15
 */
public interface UserService extends IService<User> {

    /**
     * 描述  登录
     *
     * @author Lorenzo Lin
     * @params   账号 密码
     * @created 2020/1/15 11:52
     **/
    ResultUtil login(String name, String password);
}
