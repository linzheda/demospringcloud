package com.linzd.backsystem.core.article.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linzd.backsystem.core.article.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2021-01-05
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 描述  获取文章列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2021/1/5 10:18
     **/
    IPage<Map> getList(Page<Map> page, Map<String, Object> condition);
}
