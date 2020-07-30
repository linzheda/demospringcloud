package com.linzd.backsystem.user.controller;


import com.linzd.backsystem.annotation.UserLoginToken;
import com.linzd.backsystem.user.service.OrganizationService;
import com.linzd.backsystem.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 机构 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-07-25
 */
@Api(value = "机构控制层", tags = "机构控制层")
@RestController
@UserLoginToken
@RequestMapping("/user/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService service;


    @ApiOperation(value = "获取组织机构通过pid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "父级pid和过滤某个特定的id", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getOrganizationByPid")
    public ResultUtil getOrganizationByPid(@RequestParam Map<String, Object> condition) {
        return service.getOrganizationByPid(condition);
    }





}

