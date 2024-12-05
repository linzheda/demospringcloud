package com.linzd.backsystem.core.article.controller;


import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.basecore.common.enums.OperType;
import com.linzd.backsystem.core.article.entity.ArticleType;
import com.linzd.backsystem.core.article.service.ArticleTypeService;
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
 * 文章类型 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2021-01-05
 */
@RestController
@Api(value = "文章类型控制层", tags = "文章类型控制层")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/article/articleType")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService service;

    @ApiOperation(value = "获取文章类型通过pid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "父级pid和过滤某个特定的id", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getListByPid")
    public ResultPojo getListByPid(@RequestParam Map<String, Object> condition) {
        return service.getListByPid(condition);
    }


    @ApiOperation(value = "编辑文章类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entity", value = "文章类型", required = true, dataType = "ArticleType")
    })
    @PostMapping(value = "/edit")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo edit(ArticleType entity) {
        String msg =  entity.getId() != null  ? "编辑" : "新增";
        entity.setUpdatetime(null);
        entity.setUpdateby(null);
        boolean isSuccess = entity.insertOrUpdate();
        msg += isSuccess ? "成功" : "失败";
        if(entity.getPid()!=null&&entity.getPid()!=0){
            //说明有父级
            ArticleType p=new ArticleType().selectById(entity.getPid());
            entity.setIsn(p.getIsn() + "." + entity.getId());
            entity.setLevel(p.getLevel() + 1);
        }else{
            //说明没有父级
            entity.setPid(0L);
            entity.setLevel(1);
            entity.setIsn( "0." + entity.getId());
        }
        entity.updateById();
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("id", entity.getId());
        return ResultPojo.success(msg, result);
    }


    @ApiOperation(value = "删除文章类型和下级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "字典id", required = true, dataType = "Long"),
    })
    @PostMapping(value = "/delById")
    @OperLog(type = OperType.DELETE)
    public ResultPojo delById(Long id) {
        return service.delById(id);
    }

}

