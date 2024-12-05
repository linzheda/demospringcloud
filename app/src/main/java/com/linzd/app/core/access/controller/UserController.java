package com.linzd.app.core.access.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linzd.app.core.access.entity.User;
import com.linzd.app.core.access.service.UserService;
import com.linzd.app.core.pub.entity.SysParam;
import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.annotation.PassToken;
import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.basecore.common.enums.OperType;
import com.linzd.basecore.utils.EncryptUtil;

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


    @ApiOperation(value = "登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")
    })
    @PostMapping(value = "/loginOut")
    public ResultPojo loginOut(Long id) {
        return service.loginOut(id);
    }

    @ApiOperation(value = "发送短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tel", value = "手机号码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "Integer")
    })
    @PassToken
    @PostMapping(value = "/sendSms")
    public ResultPojo sendSms(String tel,Integer type) {
        return service.sendSms(tel,type);
    }


    @ApiOperation(value = "校验短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tel", value = "手机号码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sms", value = "验证码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "Integer")
    })
    @PassToken
    @PostMapping(value = "/checkSms")
    public ResultPojo checkSms(String tel,String sms,Integer type) {
        return service.checkSms(tel,sms,type);
    }

    @ApiOperation(value = "忘记密码,修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tel", value = "手机号码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PassToken
    @PostMapping(value = "/forgetPassWord")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo forgetPassWord(String tel,String password) {
        return service.forgetPassWord(tel,password);
    }


    @ApiOperation(value = "编辑用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "User")
    })
    @PassToken
    @PostMapping(value = "/register")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo register(User user) {
        if (user.getPassword() == null) {
            //设置默认密码
            QueryWrapper<SysParam> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("code", "password");
            SysParam defaultPassword = new SysParam().selectOne(queryWrapper);
            String md5Password = EncryptUtil.md5(defaultPassword.getValue());
            user.setPassword(md5Password);
        }
        user.setUpdateby(null);
        user.setUpdatetime(null);
        boolean isSuccess = user.insertOrUpdate();
        String msg = isSuccess ? "注册成功" : "注册失败";
        return ResultPojo.success(msg, isSuccess);
    }



}

