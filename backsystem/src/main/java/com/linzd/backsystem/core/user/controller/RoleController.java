package com.linzd.backsystem.core.user.controller;


import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.common.enums.OperType;
import com.linzd.backsystem.core.user.entity.Role;
import com.linzd.backsystem.core.user.service.RoleResourcesService;
import com.linzd.backsystem.core.user.service.RoleService;
import com.linzd.backsystem.core.user.service.RoleUserService;
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
 *  前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
@Api(value = "角色管理", tags = "角色控制层")
@Transactional(rollbackFor=Exception.class)
@CheckToken
@RestController
@RequestMapping("/user/role")
public class RoleController {
    @Autowired
    private RoleService service;
    @Autowired
    private RoleResourcesService roleResourcesService;
    @Autowired
    private RoleUserService roleUserService;


    @ApiOperation(value = "获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getRoleList")
    public ResultPojo getRoleList(@RequestParam Map<String,Object> condition) {
        return service.getRoleList(condition);
    }

    @ApiOperation(value = "编辑角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "角色", required = true, dataType = "Role")
    })
    @PostMapping(value = "/editRole")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo editRole(Role role){
        boolean isInsert=role.getId() != null ? false:true;
        String msg = isInsert ? "新增" : "编辑";
        role.setUpdatetime(null);
        role.setUpdateby(null);
        boolean isSuccess =role.insertOrUpdate();
        msg += isSuccess ? "成功" : "失败";
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("id", role.getId());
        return ResultPojo.success(msg, result);
    }

    @ApiOperation(value = "删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "Long")
    })
    @PostMapping(value = "/delRole")
    @OperLog(type = OperType.DELETE)
    public ResultPojo delRole(Long id){
        //删除角色
        boolean isSuccess=service.removeById(id);
        //删除角色资源关联表
        roleResourcesService.delRoleResourcesLink();
        //删除角色用户关联表
        roleUserService.delRoleUserLink();
        String msg =isSuccess ? "删除成功" : "删除失败";
        return ResultPojo.success(msg,isSuccess);
    }

}

