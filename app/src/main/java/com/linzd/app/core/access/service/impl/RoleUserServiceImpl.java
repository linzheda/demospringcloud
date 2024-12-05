package com.linzd.app.core.access.service.impl;

import com.linzd.app.core.access.service.RoleUserService;
import com.linzd.app.core.access.entity.RoleUser;
import com.linzd.app.core.access.mapper.RoleUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色用户关联 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements RoleUserService {

}
