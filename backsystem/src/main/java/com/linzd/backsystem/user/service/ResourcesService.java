package com.linzd.backsystem.user.service;

import com.linzd.backsystem.user.entity.Resources;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.backsystem.utils.ResultUtil;

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
    ResultUtil getResourcesByUserId(Integer userId);
}
