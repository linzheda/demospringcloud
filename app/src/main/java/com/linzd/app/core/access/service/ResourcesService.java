package com.linzd.app.core.access.service;

import com.linzd.app.core.access.entity.Resources;
import com.linzd.basecore.common.entity.ResultPojo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
public interface ResourcesService extends IService<Resources> {

    /**
     * 描述  获取权限菜单通过用户id
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/27 10:02
     **/
    ResultPojo getResourcesByUserId(Map<String, Object> condition);

    /**
     * 描述  获取菜单路由 下一级
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/27 10:02
     **/
    ResultPojo getAccessRoute(Map<String, Object> condition);
}
