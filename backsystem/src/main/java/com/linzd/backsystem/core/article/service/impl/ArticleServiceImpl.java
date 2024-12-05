package com.linzd.backsystem.core.article.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.backsystem.core.article.entity.Article;
import com.linzd.backsystem.core.article.mapper.ArticleMapper;
import com.linzd.backsystem.core.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2021-01-05
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper mapper;


    /**
     * 描述  获取文章列表
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2021/1/5 10:16
     */
    @Override
    public ResultPojo getList(Map<String, Object> condition) {
        long current= Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current,size);
        IPage<Map> result=mapper.getList(page,condition);
        return ResultPojo.success(result);
    }
}
