package com.linzd.backsystem.user.service.impl;

import com.linzd.backsystem.user.entity.RoleUser;
import com.linzd.backsystem.user.mapper.RoleUserMapper;
import com.linzd.backsystem.user.service.RoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements RoleUserService {

    @Autowired
    private RoleUserMapper mapper;

    /**
     * 描述  删除无效的roleuser的关联数据
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/12 10:49
     **/
    @Override
    public ResultUtil delRoleUserLink() {
        int result=mapper.delRoleUserLink();
        return ResultUtil.success(result);
    }
}
