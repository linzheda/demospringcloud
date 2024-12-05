package com.linzd.backsystem.core.user.service;

import com.linzd.backsystem.core.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.basecore.common.entity.ResultPojo;

import java.util.Map;

/**
 * <p>
 * 服务类
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
    ResultPojo login(String name, String password);

    /**
     * 描述  修改面膜
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/25 18:07
     **/
    ResultPojo updatePassword(Long id, String oldPassword, String newPassword);


    /**
     * 描述  登出
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/21 16:08
     **/
    ResultPojo loginOut(Long id);

    /**
     * 描述  获取用户列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/25 18:07
     **/
    ResultPojo getUserList(Map<String, Object> condition);


    /**
     * 描述  获取在线用户列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/21 16:56
     **/
    ResultPojo getOnlineUserList(Map<String, Object> condition);


}
