package com.linzd.app.core.access.mapper;

import com.linzd.app.core.access.entity.RoleUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色用户关联 Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@Mapper
public interface RoleUserMapper extends BaseMapper<RoleUser> {

}
