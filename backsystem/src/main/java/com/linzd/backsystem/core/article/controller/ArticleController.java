package com.linzd.backsystem.core.article.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linzd.backsystem.core.article.entity.Article;
import com.linzd.backsystem.core.article.entity.ArticleComment;
import com.linzd.backsystem.core.article.entity.ArticleRead;
import com.linzd.backsystem.core.article.service.ArticleService;
import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.basecore.common.enums.OperType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2021-01-05
 */
@RestController
@Api(value = "文章表控制层", tags = "文章表控制层")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/article/article")
public class ArticleController {
    @Autowired
    private ArticleService service;

    @ApiOperation(value = "获取文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getList")
    public ResultPojo getList(@RequestParam Map<String, Object> condition) {
        return service.getList(condition);
    }


    @ApiOperation(value = "编辑文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entity", value = "文章", required = true, dataType = "Article")
    })
    @PostMapping(value = "/edit")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo edit(Article entity) {
        String msg = entity.getId() != null ? "编辑" : "新增";
        entity.setUpdatetime(null);
        entity.setUpdateby(null);
        boolean isSuccess = entity.insertOrUpdate();
        msg += isSuccess ? "成功" : "失败";
        Map<String, Object> result = new HashMap<>(2);
        result.put("isSuccess", isSuccess);
        result.put("id", entity.getId());
        return ResultPojo.success(msg, result);
    }


    @ApiOperation(value = "删除文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "Long")
    })
    @PostMapping(value = "/delById")
    @OperLog(type = OperType.DELETE)
    public ResultPojo delById(Long id) {
        Article article = service.getById(id);
        boolean isSuccess = service.removeById(id);
        QueryWrapper<ArticleRead> readWrapper = new QueryWrapper<>();
        readWrapper.eq("articleid", article.getId());
        new ArticleRead().delete(readWrapper);
        QueryWrapper<ArticleComment> commentWrapper = new QueryWrapper<>();
        commentWrapper.eq("articleid", article.getId());
        new ArticleComment().delete(commentWrapper);
        String msg = isSuccess ? "删除成功" : "删除失败";
        return ResultPojo.success(msg, isSuccess);
    }
}

