package com.linzd.app.core.pub.service;


import com.linzd.app.core.access.entity.User;
import com.linzd.app.core.pub.entity.Dictionary;
import com.linzd.app.core.pub.entity.SysParam;

import java.util.List;

/**
 * 描述 通用业务层
 *
 * @author Lorenzo Lin
 * @created 2020年05月27日 15:52
 */
public interface PubService {
    /**
     * 描述  获取当前请求的token 解析出来的id
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/13 17:18
     **/
    Long getUserIdByToken();


    /**
     * 描述  根据token获取用户信息
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/21 10:40
     **/
    User getUserInfoByToken(String token);

    /**
     * 描述 获取字典
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/21 10:36
     **/
    List<Dictionary> getDict(String key, Integer level);


    /**
     * 描述  获取系统参数
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/21 10:38
     **/
    SysParam getSysParam(String code);


}
