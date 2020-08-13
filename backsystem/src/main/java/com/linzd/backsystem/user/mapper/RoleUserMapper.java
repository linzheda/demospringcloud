package com.linzd.backsystem.user.mapper;

import com.linzd.backsystem.user.entity.RoleUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
}
