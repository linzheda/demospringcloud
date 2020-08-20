package com.linzd.backsystem.core.user.service;

import com.linzd.backsystem.core.user.entity.Organization;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.backsystem.utils.ResultUtil;

import java.util.Map;

/**
 * <p>
 * 机构 服务类
 * </p>
 *
 * @author linzd
 * @since 2020-07-25
 */
public interface OrganizationService extends IService<Organization> {

    /**
     * 描述  获取组织机构根据pid
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/25 15:13
     **/
    ResultUtil getOrganizationByPid(Map<String, Object> condition);
    
    /**
     * 描述  删除组织机构和下级
     *
     * @author Lorenzo Lin
     * @params  
     * @created 2020/8/19 19:53
     **/
    ResultUtil delOrganization(Long id);
}
