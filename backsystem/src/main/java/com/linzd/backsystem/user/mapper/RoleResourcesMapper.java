package com.linzd.backsystem.user.mapper;

import com.linzd.backsystem.user.entity.RoleResources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
}
