package com.linzd.backsystem.core.dictionary.controller;


import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.common.enums.OperType;
import com.linzd.backsystem.core.dictionary.entity.Dictionary;
import com.linzd.backsystem.core.dictionary.service.DictionaryService;
import com.linzd.basecore.common.entity.ResultPojo;
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
 * 字典 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-05-27
 */
@Api(value = "数据字典管理", tags = "数据字典控制层")
@RestController
@Transactional(rollbackFor=Exception.class)
@CheckToken
@RequestMapping("/dictionary/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @ApiOperation(value = "获取数据字典通过pid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "父级pid和过滤某个特定的id", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getDictsByPid")
    public ResultPojo getDictsByPid(@RequestParam Map<String, Object> condition) {
        return dictionaryService.getDictsByPid(condition);
    }

    @ApiOperation(value = "编辑字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictionary", value = "字典", required = true, dataType = "Dictionary"),
    })
    @PostMapping(value = "/editDict")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo editDict(Dictionary dictionary) {
        String msg =  dictionary.getId() != null  ? "编辑" : "新增";
        if(dictionary.getPid()==null){
            dictionary.setPid(0L);
            dictionary.setLevel(0);
        }
        boolean isSuccess = dictionary.insertOrUpdate();
        msg += isSuccess ? "成功" : "失败";
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("id", dictionary.getId());
        return ResultPojo.success(msg, result);
    }


    @ApiOperation(value = "删除字典和下级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "字典id", required = true, dataType = "Long"),
    })
    @PostMapping(value = "/delDict")
    @OperLog(type = OperType.DELETE)
    public ResultPojo delDict(Long id) {
        return dictionaryService.delDict(id);
    }
}

