package com.linzd.backsystem.core.article.mapper;

import com.linzd.backsystem.core.article.entity.ArticleType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章类型 Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2021-01-05
 */
public interface ArticleTypeMapper extends BaseMapper<ArticleType> {

    /**
     * 描述 根据pid 获取文章类型
     *
     * @author Lorenzo Lin
     * @params
     * @created 2021/1/5 10:31
     **/
    List<Map<String, Object>> getListByPid(Map<String, Object> condition);

    /**
     * 描述  删除文章类型和下级
     *
     * @author Lorenzo Lin
     * @params
     * @created 2021/1/5 10:41
     **/
    int delById(Long id);
}
