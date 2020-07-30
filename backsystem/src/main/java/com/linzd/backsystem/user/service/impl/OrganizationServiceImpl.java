package com.linzd.backsystem.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.user.entity.Organization;
import com.linzd.backsystem.user.mapper.OrganizationMapper;
import com.linzd.backsystem.user.service.OrganizationService;
import com.linzd.backsystem.utils.ResultUtil;
import com.linzd.backsystem.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-07-25
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Autowired
    private OrganizationMapper mapper;

    /**
     * 描述  根据pid获取组织机构
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/25 15:14
     **/
    @Override
    public ResultUtil getOrganizationByPid(Map<String, Object> condition) {
        List<Map<String,Object>> list=mapper.getOrganizationByPid(condition);
        TreeUtils tu=new TreeUtils();
        List<Map<String,Object>> tree=tu.toMapTree(list,null);
        return ResultUtil.success(tree);
    }
}
