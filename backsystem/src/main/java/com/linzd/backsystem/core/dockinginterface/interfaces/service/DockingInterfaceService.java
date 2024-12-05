package com.linzd.backsystem.core.dockinginterface.interfaces.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.backsystem.core.user.entity.Organization;
import com.linzd.backsystem.core.user.entity.Resources;
import com.linzd.backsystem.core.user.entity.Role;
import com.linzd.backsystem.core.user.entity.User;
import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.backsystem.core.dockinginterface.manager.entity.DockingInterface;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 对外接口表 服务类
 * </p>
 *
 * @author linzd
 * @since 2020-08-20
 */
public interface DockingInterfaceService extends IService<DockingInterface> {

    /**
     * 描述  获取访问令牌
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 10:39
     **/
    ResultPojo getToken(String name, String password);

    /**
     * 描述  根据token获取用户信息
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 11:38
     **/
    ResultPojo getUserInfoByToken();

    /**
     * 描述  获取用户列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 11:42
     **/
    ResultPojo getUserList(Map<String, Object> condition);

    /**
     * 描述  编辑用户
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 14:40
     **/
    ResultPojo editUser(User user);

    /**
     * 描述  获取组织机构列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 15:09
     **/
    ResultPojo getOrganizationList(Map<String, Object> condition);

    /**
     * 描述  编辑组织机构
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 15:40
     **/
    ResultPojo editOrganization(Organization organization);

    /**
     * 描述  获取菜单列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 15:57
     **/
    ResultPojo getResourcesList(Map<String, Object> condition);

    /**
     * 描述  编辑菜单
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 16:17
     **/
    ResultPojo editResources(Resources resources);

    /**
     * 描述  获取角色列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 16:24
     **/
    ResultPojo getRoleList(Map<String, Object> condition);

    /**
     * 描述  编辑角色
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 16:44
     **/
    ResultPojo editRole(Role role);

    /**
     * 描述  获取资源列表根据角色id
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 16:47
     **/
    ResultPojo getResourcesListByRoleId(Map<String, Object> condition);

    /**
     * 描述  分配角色资源列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 16:56
     **/
    ResultPojo updateRoleResourcesByRoleId(Long roleid, List<Long> addArr, List<Long> delArr);

    /**
     * 描述  获取这个角色下的用户列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 17:01
     **/
    ResultPojo getUserListByRoleId(Map<String, Object> condition);

    /**
     * 描述  更新用户角色表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 17:04
     **/
    ResultPojo updateRoleUserByRoleId(Long roleid, List<Long> addArr, List<Long> delArr);

    /**
     * 描述  获取这个用户下的角色列表(全部)
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/3 15:01
     **/
    ResultPojo getRoleListByUserId(Map<String, Object> condition);

    /**
     * 描述  修改RoleUser表通过用户id
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/3 15:18
     **/
    ResultPojo updateRoleUserByUserId(Long userid, List<Long> addArr, List<Long> delArr);
}
