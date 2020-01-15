package com.linzd.login.login.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linzd.login.annotation.PassToken;
import com.linzd.login.annotation.UserLoginToken;
import com.linzd.login.login.entity.User;
import com.linzd.login.login.service.UserService;
import com.linzd.login.utils.Encrypt;
import com.linzd.login.utils.JwtTokenUtil;
import com.linzd.login.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-01-15
 */
@Api(value = "用户控制器", tags = {"用户控制器"})
@RestController
@RequestMapping("/userCtr")
@UserLoginToken
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserService userservice;


    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "登录名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PostMapping(value = "/login")
    @PassToken
    public @ResponseBody
    ResultUtil login(String name, String password) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            return ResultUtil.error("用户名或密码不允许为空");
        }
        String md5Password = Encrypt.md5AndSha(password);
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("loginname",name);
        queryWrapper.eq("password",md5Password);
        User user = null;
        try {
            user = userservice.getOne(queryWrapper);
            String token = JwtTokenUtil.sign(user.getId());
            user.setToken(token);
        } catch (Exception e) {
            user = null;
            return ResultUtil.error(e.toString());
        }
        if (user != null) {
            return ResultUtil.success(user);
        } else {
            return ResultUtil.error("用户名或密码错误..");
        }

    }
}

