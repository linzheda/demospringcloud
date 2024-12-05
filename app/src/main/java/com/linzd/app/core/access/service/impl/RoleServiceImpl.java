package com.linzd.app.core.access.service.impl;

import com.linzd.app.core.access.entity.Role;
import com.linzd.app.core.access.service.RoleService;
import com.linzd.app.core.access.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
