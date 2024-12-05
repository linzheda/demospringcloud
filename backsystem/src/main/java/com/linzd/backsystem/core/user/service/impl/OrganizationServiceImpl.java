package com.linzd.backsystem.core.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.core.user.entity.Organization;
import com.linzd.backsystem.core.user.mapper.OrganizationMapper;
import com.linzd.backsystem.core.user.service.OrganizationService;
import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.basecore.utils.TreeUtil;
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
    public ResultPojo getOrganizationByPid(Map<String, Object> condition) {
        List<Map<String,Object>> list=mapper.getOrganizationByPid(condition);
        TreeUtil tu=new TreeUtil();
        List<Map<String,Object>> tree=tu.toMapTree(list,null);
        return ResultPojo.success(tree);
    }

    /**
     * 描述  删除组织机构和下级
     *
     * @param id
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/19 19:53
     */
    @Override
    public ResultPojo delOrganization(Long id) {
        //删除资源
        int result= mapper.delOrganization(id);
        String msg = result >0 ? "删除成功" : "删除失败";
        return ResultPojo.success(msg, result);
    }
}
