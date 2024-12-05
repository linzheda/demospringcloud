package com.linzd.backsystem.core.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.backsystem.core.user.entity.Role;
import com.linzd.basecore.common.entity.ResultPojo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
public interface RoleService extends IService<Role> {

    /**
     * 描述  获取角色列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/11 15:57
     **/
    ResultPojo getRoleList(Map<String,Object> condition);
}
