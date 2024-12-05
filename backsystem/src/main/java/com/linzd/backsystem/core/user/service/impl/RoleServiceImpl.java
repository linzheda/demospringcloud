package com.linzd.backsystem.core.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.core.user.entity.Role;
import com.linzd.backsystem.core.user.mapper.RoleMapper;
import com.linzd.backsystem.core.user.service.RoleService;
import com.linzd.basecore.common.entity.ResultPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper mapper;

    /**
     * 描述  获取角色列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/11 15:58
     **/
    @Override
    public ResultPojo getRoleList(Map<String,Object> condition) {
        long current= Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current,size);
        IPage<Map> result = mapper.getRoleList(page,condition);
        return ResultPojo.success(result);
    }
}
