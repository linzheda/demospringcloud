package com.linzd.app.core.access.service;

import com.linzd.app.common.entity.ResultPojo;
import com.linzd.app.core.access.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
public interface UserService extends IService<User> {

    /**
     * 描述  登录
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/24 15:34
     **/
    ResultPojo login(String name, String password);

    /**
     * 描述  修改密码
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/24 15:34
     **/
    ResultPojo updatePassword(Long id, String oldPassword, String newPassword);
}
