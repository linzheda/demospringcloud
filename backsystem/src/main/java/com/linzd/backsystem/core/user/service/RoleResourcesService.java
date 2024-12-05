package com.linzd.backsystem.core.user.service;

import com.linzd.backsystem.core.user.entity.RoleResources;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.basecore.common.entity.ResultPojo;

import java.util.Map;

/**
 * <p>
 *  角色资源服务类
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
public interface RoleResourcesService extends IService<RoleResources> {
    /**
     * 描述  删除无效的RoleResources
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/11 21:07
     **/
    ResultPojo delRoleResourcesLink();

    /**
     * 描述  根据角色id获取资源列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/14 18:54
     **/
    ResultPojo getResourcesListByRoleId(Map<String, Object> condition);
}
