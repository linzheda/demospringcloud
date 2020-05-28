package com.linzd.backsystem.user.controller;


import com.linzd.backsystem.annotation.UserLoginToken;
import com.linzd.backsystem.user.entity.Resources;
import com.linzd.backsystem.user.service.ResourcesService;
import com.linzd.backsystem.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
@Api(value = "权限菜单控制层", tags = "权限菜单控制层")
@RestController
@UserLoginToken
@RequestMapping("/user/resources")
public class ResourcesController {
    @Autowired
    private ResourcesService resourcesService;

    @ApiOperation(value = "获取权限菜单通过用户id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "用户userid和pid", required = true, dataType = "Integer"),
    })
    @PostMapping(value = "/getResourcesByUserId")
    public ResultUtil getResourcesByUserId(@RequestParam Map<String, Object> condition) {
        return resourcesService.getResourcesByUserId(condition);
    }

    @ApiOperation(value = "获取权限菜单通过pid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "父级id", required = true, dataType = "Long"),
    })
    @PostMapping(value = "/getResourcesByPid")
    public ResultUtil getResourcesByPid(Long pid) {
        return resourcesService.getResourcesByPid(pid);
    }


    @ApiOperation(value = "编辑菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resources", value = "菜单", required = true, dataType = "Resources"),
    })
    @PostMapping(value = "/editResources")
    public ResultUtil editResources(Resources resources){
        boolean  result= resources.insertOrUpdate();
        String msg = result ? "编辑成功" : "编辑失败";
        return ResultUtil.success(msg,result);
    }


}

