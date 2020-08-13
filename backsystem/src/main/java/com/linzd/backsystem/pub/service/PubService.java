package com.linzd.backsystem.pub.service;

/**
 * 描述 通用业务层
 *
 * @author Lorenzo Lin
 * @created 2020年05月27日 15:52
 */
public interface PubService  {
    /**
     * 描述  获取当前请求的token 解析出来的id
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/13 17:18
     **/
    Long getUserIdByToken();
}
