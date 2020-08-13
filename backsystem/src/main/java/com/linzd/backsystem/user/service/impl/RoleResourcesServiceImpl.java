package com.linzd.backsystem.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.user.entity.RoleResources;
import com.linzd.backsystem.user.mapper.RoleResourcesMapper;
import com.linzd.backsystem.user.service.RoleResourcesService;
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
public class RoleResourcesServiceImpl extends ServiceImpl<RoleResourcesMapper, RoleResources> implements RoleResourcesService {
    @Autowired
    private RoleResourcesMapper mapper;

    /**
     * 描述  删除无效的RoleResources的关联关系
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/12 10:24
     **/
    @Override
    public ResultUtil delRoleResourcesLink() {
        int result= mapper.delRoleResourcesLink();
        return ResultUtil.success(result);
    }
}
