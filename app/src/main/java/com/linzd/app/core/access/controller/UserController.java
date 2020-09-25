package com.linzd.app.core.access.controller;


import com.linzd.app.annotation.CheckToken;
import com.linzd.app.annotation.OperLog;
import com.linzd.app.annotation.PassToken;
import com.linzd.app.common.entity.ResultPojo;
import com.linzd.app.common.enums.OperType;
import com.linzd.app.core.access.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@RestController
@Api(value = "用户模块", tags = "用户模块")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/access/user")
public class UserController {
    @Autowired
    private UserService service;

    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "登录名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PostMapping(value = "/login")
    @PassToken
    @OperLog(type = OperType.LOGIN)
    public ResultPojo login(String name, String password) {
        return service.login(name, password);
    }

    @ApiOperation(value = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String")
    })
    @PostMapping(value = "/updatePassword")
    public ResultPojo updatePassword(Long id, String oldPassword, String newPassword) {
        return service.updatePassword(id, oldPassword, newPassword);
    }
}

