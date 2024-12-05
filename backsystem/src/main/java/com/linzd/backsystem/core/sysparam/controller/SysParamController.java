package com.linzd.backsystem.core.sysparam.controller;


import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.common.enums.OperType;
import com.linzd.backsystem.core.sysparam.entity.SysParam;
import com.linzd.backsystem.core.sysparam.service.SysParamService;
import com.linzd.basecore.common.entity.ResultPojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 系统参数 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-08-07
 */
@Api(value = "系统管理", tags = "系统参数控制层")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RestController
@RequestMapping("/sysparam/sysparam")
public class SysParamController {

    @Autowired
    private SysParamService service;

    @ApiOperation(value = "获取系统参数列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getSysParamList")
    @Cacheable(value = "sysparam")
    public ResultPojo getSysParamList(@RequestParam Map<String, Object> condition) {
        return service.getSysParamList(condition);
    }

    @ApiOperation(value = "编辑系统参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysParam", value = "参数", required = true, dataType = "SysParam")
    })
    @PostMapping(value = "/editSysparam")
    @OperLog(type = OperType.UPDATE)
    @CacheEvict(cacheNames="sysparam", allEntries=true)
    public ResultPojo editSysparam(SysParam sysParam) {
        boolean isInsert=sysParam.getId() != null ? false:true;
        String msg = isInsert ? "新增" : "编辑";
        sysParam.setUpdateby(null);
        sysParam.setUpdatetime(null);
        boolean isSuccess =sysParam.insertOrUpdate();
        msg += isSuccess ? "成功" : "失败";
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("id", sysParam.getId());
        return ResultPojo.success(msg, result);
    }


    @ApiOperation(value = "删除参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "参数id", required = true, dataType = "Long")
    })
    @PostMapping(value = "/delSysparam")
    @OperLog(type = OperType.DELETE)
    @CacheEvict(cacheNames="sysparam", allEntries=true)
    public ResultPojo delSysparam(Long id){
        //删除用户
        boolean isSuccess=service.removeById(id);
        String msg =isSuccess ? "删除成功" : "删除失败";
        return ResultPojo.success(msg,isSuccess);
    }


}

