package com.linzd.backsystem.user.service;

import com.linzd.backsystem.user.entity.Resources;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.backsystem.utils.ResultUtil;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
public interface ResourcesService extends IService<Resources> {

    /**
     * 描述  获取资源菜单通过用户id
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/3/23 11:14
     **/
    ResultUtil getResourcesByUserId(Map<String,Object> condition);

    
    /**
     * 描述 获取资源 根据父级id
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/5/26 11:53
     **/
    ResultUtil getResourcesByPid(Map<String,Object> condition);
}
