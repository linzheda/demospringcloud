package com.linzd.mobile.controllers;

import com.linzd.mobile.annotation.PassToken;
import com.linzd.mobile.annotation.UserLoginToken;
import com.linzd.mobile.entity.User;
import com.linzd.mobile.feigns.PcFeignClient;
import com.linzd.mobile.service.UserService;
import com.linzd.mobile.util.Encrypt;
import com.linzd.mobile.util.JwtTokenUtil;
import com.linzd.mobile.util.ResultUtil;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 描述
 *
 * @author Lorenzo Lin
 * @created 2019年10月10日 17:30
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
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

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

    @Autowired
    private PcFeignClient pcFeignClient;

    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/getUserInfo")
    public @ResponseBody
    ResultUtil getUserInfo(Integer id) {
        User user = userservice.findById(id);
        Map<String, Object> result = userservice.test();
        System.out.println("66666:" + result);
        return ResultUtil.success(result);
    }


    /**
     * 描述  这个是消费者 调用pc项目的接口
     *
     * @author Lorenzo Lin
     * @params
     * @created 2019/12/10 11:54
     **/
    @PassToken
    @GetMapping("/consumer")
    public @ResponseBody
    Object consumer() {
        return this.pcFeignClient.getUserInfo(1);
    }

    @PassToken
    @GetMapping("/test")
    public @ResponseBody
    Object test() {
        return this.pcFeignClient.getUserInfo(1);
    }

    @PassToken
    @GetMapping("/test2/{id}")
    public @ResponseBody
    Object test2(@PathVariable Integer id) {
        return this.pcFeignClient.getUserInfo2(id);
    }
}
