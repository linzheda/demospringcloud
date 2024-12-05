package com.linzd.app.core.access.service;

import com.linzd.basecore.common.entity.ResultPojo;
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

    /**
     * 描述  登出
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/21 16:08
     **/
    ResultPojo loginOut(Long id);

    /**
     * 描述  发送验证码
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/10/15 14:46
     **/
    ResultPojo sendSms(String tel,Integer type);
    
    /**
     * 描述  校验短信验证码
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/10/26 11:26
     **/
    ResultPojo checkSms(String tel, String sms,Integer type);

    /**
     * 描述  忘记密码 修改
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/10/27 9:18
     **/
    ResultPojo forgetPassWord(String tel, String password);


}
