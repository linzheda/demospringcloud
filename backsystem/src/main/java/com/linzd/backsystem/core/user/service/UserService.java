package com.linzd.backsystem.core.user.service;

import com.linzd.backsystem.core.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.backsystem.utils.ResultUtil;

import java.util.Map;

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

    /**
     * 描述  修改面膜
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/25 18:07
     **/
    ResultUtil updatePassword(Long id, String oldPassword, String newPassword);

    /**
     * 描述  获取用户列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/25 18:07
     **/
    ResultUtil getUserList(Map<String, Object> condition);
}
