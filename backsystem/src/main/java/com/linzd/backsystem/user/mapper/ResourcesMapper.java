package com.linzd.backsystem.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linzd.backsystem.common.entity.Tree;
import com.linzd.backsystem.user.entity.Resources;

import java.util.List;

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
    List<Tree> getResourcesByUserId(Integer userId);
}
