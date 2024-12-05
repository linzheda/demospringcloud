package com.linzd.app.core.pub.controller;

import com.linzd.app.core.pub.service.PubService;
import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.PassToken;
import com.linzd.basecore.common.entity.ResultPojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述 通用方法的控制层
 *
 * @author Lorenzo Lin
 * @created 2020年05月27日 15:50
 */
@Api(value = "通用方法的控制层", tags = "通用方法的控制层")
@RestController
@CheckToken
@RequestMapping("/pub/pubCtr")
public class PubController {
    @Autowired
    private PubService service;

    @ApiOperation(value = "获取字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "字典key", required = true, dataType = "String"),
            @ApiImplicitParam(name = "level", value = "字典等级", required = true, dataType = "Integer"),
    })
    @PostMapping(value = "/getDict")
    @PassToken
    public ResultPojo getDict(String key, Integer level) {
        return ResultPojo.success(service.getDict(key,level));
    }


    @ApiOperation(value = "获取系统参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "参数编码", required = true, dataType = "String")
    })
    @PostMapping(value = "/getSysParam")
    @PassToken
    public ResultPojo getSysParam(String code) {
        return ResultPojo.success(service.getSysParam(code));
    }


    @ApiOperation(value = "根据token获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String")
    })
    @PostMapping(value = "/getUserInfoByToken")
    @PassToken
    public ResultPojo getUserInfoByToken(String token) {
        return ResultPojo.success(service.getUserInfoByToken(token));
    }




}
