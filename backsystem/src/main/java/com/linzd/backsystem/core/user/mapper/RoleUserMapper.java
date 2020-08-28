package com.linzd.backsystem.core.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linzd.backsystem.core.user.entity.RoleUser;

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
public interface RoleUserMapper extends BaseMapper<RoleUser> {

    /**
     * 描述  删除无效的roleuser的关联数据
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/12 10:53
     **/
    int delRoleUserLink();

    /**
     * 描述  获取这个角色下的用户列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/17 16:50
     **/
    List<Map> getUserListByRoleId(Map<String, Object> condition);
    
    /**
     * 描述  获取用户列表(分页)
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/18 10:36
     **/
    IPage<Map> getUserListByCondition(Page<Map> page, Map<String, Object> condition);

    /**
     * 描述  获取这个用户下的角色列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/27 16:11
     **/
    List<Map> getRoleListByUserId(Map<String, Object> condition);

    /**
     * 描述  获取角色列表分页
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/27 16:16
     **/
    IPage<Map> getRoleListByCondition(Page<Map> page, Map<String, Object> condition);
}
