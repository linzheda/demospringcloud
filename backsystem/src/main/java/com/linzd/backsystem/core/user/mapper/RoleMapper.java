package com.linzd.backsystem.core.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linzd.backsystem.core.user.entity.Role;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 描述  获取角色列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/11 16:58
     **/
    IPage<Map> getRoleList(Page page,Map<String,Object> condition);
}
