package com.linzd.backsystem.core.user.service;

import com.linzd.backsystem.core.user.entity.RoleUser;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface RoleUserService extends IService<RoleUser> {

    /**
     * 描述  删除无效的roleuser关联表的数据
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/12 10:49
     **/
    ResultPojo delRoleUserLink();


    /**
     * 描述  获取这个角色下的用户列表(全部)
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/17 16:48
     **/
    ResultPojo getUserListByRoleId(Map<String, Object> condition);


    /**
     * 描述  获取用户列表(分页)
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/18 10:35
     **/
    ResultPojo getUserListByCondition(Map<String, Object> condition);

    /**
     * 描述  获取这个用户下的角色列表(全部)
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/27 16:03
     **/
    ResultPojo getRoleListByUserId(Map<String, Object> condition);
    
    
    /**
     * 描述  获取角色列表(分页)
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/27 16:15
     **/
    ResultPojo getRoleListByCondition(Map<String, Object> condition);
}
