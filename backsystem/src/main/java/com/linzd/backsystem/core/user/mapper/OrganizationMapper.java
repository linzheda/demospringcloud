package com.linzd.backsystem.core.user.mapper;

import com.linzd.backsystem.core.user.entity.Organization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构 Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-07-25
 */
public interface OrganizationMapper extends BaseMapper<Organization> {

    /**
     * 描述  根据pid获取组织机构
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/25 15:16
     **/
    List<Map<String, Object>> getOrganizationByPid(Map<String, Object> condition);

    /**
     * 描述  删除机构和下级
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/19 19:55
     **/
    int delOrganization(Long id);
}
