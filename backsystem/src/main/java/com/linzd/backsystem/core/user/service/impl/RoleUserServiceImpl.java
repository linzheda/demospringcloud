package com.linzd.backsystem.core.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.core.user.entity.RoleUser;
import com.linzd.backsystem.core.user.mapper.RoleUserMapper;
import com.linzd.backsystem.core.user.service.RoleUserService;
import com.linzd.basecore.common.entity.ResultPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
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
    public ResultPojo delRoleUserLink() {
        int result = mapper.delRoleUserLink();
        return ResultPojo.success(result);
    }

    /**
     * 描述  获取这个角色下的用户列表
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/17 16:48
     */
    @Override
    public ResultPojo getUserListByRoleId(Map<String, Object> condition) {
        List<Map> result = mapper.getUserListByRoleId(condition);
        return ResultPojo.success(result);
    }

    /**
     * 描述  获取用户列表(分页)
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/18 10:35
     */
    @Override
    public ResultPojo getUserListByCondition(Map<String, Object> condition) {
        long current = Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current, size);
        IPage<Map> result = mapper.getUserListByCondition(page, condition);
        return ResultPojo.success(result);
    }

    /**
     * 描述  获取这个用户下的角色列表(全部)
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/27 16:03
     */
    @Override
    public ResultPojo getRoleListByUserId(Map<String, Object> condition) {
        List<Map> result = mapper.getRoleListByUserId(condition);
        return ResultPojo.success(result);
    }

    /**
     * 描述  获取角色列表(分页)
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/27 16:15
     */
    @Override
    public ResultPojo getRoleListByCondition(Map<String, Object> condition) {
        long current = Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current, size);
        IPage<Map> result = mapper.getRoleListByCondition(page, condition);
        return ResultPojo.success(result);
    }


}
