package com.linzd.backsystem.core.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.annotation.PassToken;
import com.linzd.basecore.common.enums.OperType;
import com.linzd.backsystem.core.sysparam.entity.SysParam;
import com.linzd.backsystem.core.user.entity.User;
import com.linzd.backsystem.core.user.service.RoleUserService;
import com.linzd.backsystem.core.user.service.UserService;
import com.linzd.basecore.utils.EncryptUtil;
import com.linzd.basecore.common.entity.ResultPojo;
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
 * @since 2020-01-15
 */
@Api(value = "用户管理", tags = "用户控制层")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RestController
@RequestMapping("/user/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private RoleUserService roleUserService;


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

    @ApiOperation(value = "重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")
    })
    @PostMapping(value = "/resetPassword")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo resetPassword(Long id) {
        //设置默认密码
        QueryWrapper<SysParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", "password");
        SysParam defaultPassword = new SysParam().selectOne(queryWrapper);
        String md5Password = EncryptUtil.md5(defaultPassword.getValue());
        User user = new User().selectById(id);
        user.setPassword(md5Password);
        boolean isSuccess = user.updateById();
        return ResultPojo.success("重置密码成功", isSuccess);
    }

    @ApiOperation(value = "登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")
    })
    @PostMapping(value = "/loginOut")
    public ResultPojo loginOut(Long id) {
        return service.loginOut(id);
    }


    @ApiOperation(value = "获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getUserList")
    public ResultPojo getUserList(@RequestParam Map<String, Object> condition) {
        return service.getUserList(condition);
    }

    @ApiOperation(value = "获取在线用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getOnlineUserList")
    public ResultPojo getOnlineUserList(@RequestParam Map<String, Object> condition) {
        return service.getOnlineUserList(condition);
    }

    @ApiOperation(value = "根据id强退用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "条件", required = true, dataType = "Long")
    })
    @PostMapping(value = "/loginOutById")
    @OperLog(type = OperType.FORCE)
    public ResultPojo loginOutById(Long userid) {
        return service.loginOut(userid);
    }

    @ApiOperation(value = "编辑用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "User")
    })
    @PostMapping(value = "/editUser")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo editUser(User user) {
        boolean isInsert = user.getId() == null;
        String msg = isInsert ? "新增" : "编辑";
        if (isInsert && user.getPassword() == null) {
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
        msg += isSuccess ? "成功" : "失败";
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("id", user.getId());
        return ResultPojo.success(msg, result);
    }

    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户", required = true, dataType = "Long")
    })
    @PostMapping(value = "/delUser")
    @OperLog(type = OperType.DELETE)
    public ResultPojo delUser(Long id) {
        //删除用户
        boolean isSuccess = service.removeById(id);
        //删除用户角色关联表的数据
        roleUserService.delRoleUserLink();
        String msg = isSuccess ? "删除成功" : "删除失败";
        return ResultPojo.success(msg, isSuccess);
    }


}

