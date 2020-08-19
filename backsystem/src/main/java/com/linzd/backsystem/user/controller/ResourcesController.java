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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
@Transactional(rollbackFor=Exception.class)
@UserLoginToken
@RequestMapping("/user/resources")
public class ResourcesController {
    @Autowired
    private ResourcesService service;

    @ApiOperation(value = "获取权限菜单通过用户id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "用户userid和pid", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getResourcesByUserId")
    public ResultUtil getResourcesByUserId(@RequestParam Map<String, Object> condition) {
        return service.getResourcesByUserId(condition);
    }

    @ApiOperation(value = "获取权限菜单通过pid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "父级pid和过滤某个特定的id", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getResourcesByPid")
    public ResultUtil getResourcesByPid(@RequestParam Map<String, Object> condition) {
        return service.getResourcesByPid(condition);
    }


    @ApiOperation(value = "编辑菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resources", value = "菜单", required = true, dataType = "Resources")
    })
    @PostMapping(value = "/editResources")
    public ResultUtil editResources(Resources resources) {
        String msg =  resources.getId() != null  ? "编辑" : "新增";
        resources.setUpdatetime(null);
        resources.setUpdateby(null);
        boolean isSuccess = resources.insertOrUpdate();
        msg += isSuccess ? "成功" : "失败";
        if(resources.getPid()!=null&&resources.getPid()!=0){
            //说明有父级
            Resources p=new Resources().selectById(resources.getPid());
            resources.setIsn(p.getIsn() + "." + resources.getId());
        }else{
            //说明没有父级
            resources.setPid(0L);
            resources.setIsn( "0." + resources.getId());
        }
        resources.updateById();
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("id", resources.getId());
        return ResultUtil.success(msg, result);
    }

    @ApiOperation(value = "删除菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单", required = true, dataType = "Long")
    })
    @PostMapping(value = "/delResources")
    public ResultUtil delResources(Long id) {
        return service.delResources(id);
    }


}

