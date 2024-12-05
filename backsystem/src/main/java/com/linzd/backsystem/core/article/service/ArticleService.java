package com.linzd.backsystem.core.article.service;

import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.backsystem.core.article.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author linzd
 * @since 2021-01-05
 */
public interface ArticleService extends IService<Article> {

    /**
     * 描述  获取文章列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2021/1/5 10:16
     **/
    ResultPojo getList(Map<String, Object> condition);
}
