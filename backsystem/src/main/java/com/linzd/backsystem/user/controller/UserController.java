package com.linzd.backsystem.user.controller;


import com.linzd.backsystem.annotation.PassToken;
import com.linzd.backsystem.user.service.UserService;
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
 * @since 2020-01-15
 */
@Api(value = "用户控制层",tags = "用户控制层")
@RestController
@RequestMapping("/user/userCtr")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "登录名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PostMapping(value = "/login")
    @PassToken
    public @ResponseBody
    ResultUtil login(String name, String password) {
        return userService.login(name,password);
    }
}

