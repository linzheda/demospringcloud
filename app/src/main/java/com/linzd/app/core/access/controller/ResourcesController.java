package com.linzd.app.core.access.controller;


import com.linzd.app.core.access.service.ResourcesService;
import com.linzd.basecore.annotation.CheckToken;
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

import java.util.Map;

/**
 * <p>
 * 资源 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@RestController
@Api(value = "资源控制层", tags = "资源控制层")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/access/resources")
public class ResourcesController {
    @Autowired
    private ResourcesService service;

    @ApiOperation(value = "获取权限菜单通过用户id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "用户userid和pid", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getResourcesByUserId")
    public ResultPojo getResourcesByUserId(@RequestParam Map<String, Object> condition) {
        return service.getResourcesByUserId(condition);
    }


    @ApiOperation(value = "获取访问路由通过父级路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "用户userid和pid", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getAccessRoute")
    public ResultPojo getAccessRoute(@RequestParam Map<String, Object> condition) {
        return service.getAccessRoute(condition);
    }


}

