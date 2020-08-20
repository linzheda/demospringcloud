package com.linzd.backsystem.core.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linzd.backsystem.core.user.entity.Resources;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
public interface ResourcesMapper extends BaseMapper<Resources> {

    /**
     * 描述  获取树形的资源菜单
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/3/23 11:44
     **/
    List<Map<String,Object>> getResourcesByUserId(Map<String,Object> condition);
    
    /**
     * 描述  根据父级id获取资源菜单
     *
     * @author Lorenzo Lin
     * @params  
     * @created 2020/5/26 16:30
     **/
    List<Map<String,Object>> getResourcesByPid(Map<String,Object> condition);

    /**
     * 描述  删除资源和下级
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/16 16:49
     **/
    int delResources(Long id);
}
