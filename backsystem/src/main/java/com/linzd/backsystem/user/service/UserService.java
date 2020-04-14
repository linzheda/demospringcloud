package com.linzd.backsystem.user.service;

import com.linzd.backsystem.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.backsystem.utils.ResultUtil;

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
     * @params
     * @created 2020/1/15 20:51
     **/
    ResultUtil login(String name, String password);

    ResultUtil updatePassword(Integer id, String oldPassword, String newPassword);
}
