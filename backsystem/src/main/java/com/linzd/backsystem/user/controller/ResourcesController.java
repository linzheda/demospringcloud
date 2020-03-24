package com.linzd.backsystem.user.controller;


import com.linzd.backsystem.user.service.ResourcesService;
import com.linzd.backsystem.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
@Api(value = "权限菜单控制层",tags = "权限菜单控制层")
@RestController
@RequestMapping("/user/resources")
public class ResourcesController {
    @Autowired
    private ResourcesService resourcesService;

    @ApiOperation(value = "获取权限菜单通过用户id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/getResourcesByUserId")
    public @ResponseBody
    ResultUtil getResourcesByUserId(Integer userId) {
        return resourcesService.getResourcesByUserId(userId);
    }
}

