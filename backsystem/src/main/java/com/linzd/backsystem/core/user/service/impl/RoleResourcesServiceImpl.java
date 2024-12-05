package com.linzd.backsystem.core.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.core.user.entity.RoleResources;
import com.linzd.backsystem.core.user.mapper.RoleResourcesMapper;
import com.linzd.backsystem.core.user.service.RoleResourcesService;
import com.linzd.basecore.common.entity.ResultPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public ResultPojo delRoleResourcesLink() {
        int result= mapper.delRoleResourcesLink();
        return ResultPojo.success(result);
    }

    /**
     * 描述  根据角色id获取资源列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/14 18:55
     **/
    @Override
    public ResultPojo getResourcesListByRoleId(Map<String, Object> condition) {
        List<Map<String,Object>> result = mapper.getResourcesListByRoleId(condition);
        return ResultPojo.success(result);
    }
}
