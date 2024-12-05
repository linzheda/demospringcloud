package com.linzd.backsystem.core.dockinginterface.interfaces.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linzd.backsystem.core.dockinginterface.manager.entity.DockingInterface;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 对外接口表 Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-08-20
 */
public interface DockingInterfaceMapper extends BaseMapper<DockingInterface> {

    /**
     * 描述  获取用户列表带标签
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 11:47
     **/
    IPage<Map> getUserList(Page<Map> page, Map<String, Object> condition);


    /**
     * 描述  获取组织机构列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 15:14
     **/
    IPage<Map> getOrganizationList(Page<Map> page, Map<String, Object> condition);

    /**
     * 描述  获取菜单列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 16:03
     **/
    IPage<Map> getResourcesList(Page<Map> page, Map<String, Object> condition);

    /**
     * 描述  获取角色列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 16:27
     **/
    IPage<Map> getRoleList(Page<Map> page, Map<String, Object> condition);

    /**
     * 描述  根据角色id获取资源列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 16:48
     **/
    List<Map<String, Object>> getResourcesListByRoleId(Map<String, Object> condition);

    /**
     * 描述  获取这个角色下的用户列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/21 17:02
     **/
    List<Map> getUserListByRoleId(Map<String, Object> condition);

    /**
     * 描述  获取这个用户下的所有角色
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/3 15:07
     **/
    List<Map> getRoleListByUserId(Map<String, Object> condition);
}
