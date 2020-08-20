package com.linzd.backsystem.core.user.mapper;

import com.linzd.backsystem.core.user.entity.RoleResources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
public interface RoleResourcesMapper extends BaseMapper<RoleResources> {

    /**
     * 描述  删除无效的关联数据
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/12 10:30
     **/
    int delRoleResourcesLink();

    /**
     * 描述  根据角色id获取资源列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/14 18:59
     **/
    List<Map<String, Object>> getResourcesListByRoleId(Map<String, Object> condition);
}
