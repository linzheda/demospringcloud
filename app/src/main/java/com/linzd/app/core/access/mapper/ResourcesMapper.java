package com.linzd.app.core.access.mapper;

import com.linzd.app.core.access.entity.Resources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源 Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@Mapper
public interface ResourcesMapper extends BaseMapper<Resources> {
    
    /**
     * 描述   获取树形的资源菜单
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/27 10:04
     **/
    @MapKey("id")
    List<Map<String, Object>> getResourcesByUserId(Map<String, Object> condition);

    /**
     * 描述  获取菜单路由 下一级
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/29 11:15
     **/
    @MapKey("id")
    List<Map<String, Object>> getAccessRoute(Map<String, Object> condition);
}
