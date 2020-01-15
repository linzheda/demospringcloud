package com.linzd.pc.controllers;

import com.linzd.pc.annotation.PassToken;
import com.linzd.pc.annotation.UserLoginToken;
import com.linzd.pc.entity.User;
import com.linzd.pc.service.UserService;
import com.linzd.pc.util.Encrypt;
import com.linzd.pc.util.JwtTokenUtil;
import com.linzd.pc.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 描述  用户控制器
 *
 * @author Lorenzo Lin
 * @created 2019年10月10日 17:30
 */
@Api(value = "用户控制器", tags = {"用户控制器"})
@Controller
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
        User user = null;
        try {
            user = userservice.getUser(name, md5Password);
            String token = JwtTokenUtil.sign(user.getId().longValue());
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

    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/getUserInfo")
    public @ResponseBody
    ResultUtil getUserInfo(Integer id) {
        System.out.println("进入pc 的 getUserInfo 方法");
        User user = userservice.findById(id);
        return ResultUtil.success(user);
    }

    @GetMapping("/getUserInfo2/{id}")
    public @ResponseBody
    ResultUtil getUserInfo2(@PathVariable Integer id) {
        System.out.println("进了pc的模块");
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user = userservice.findById(id);
        return ResultUtil.success(user);
    }

}
