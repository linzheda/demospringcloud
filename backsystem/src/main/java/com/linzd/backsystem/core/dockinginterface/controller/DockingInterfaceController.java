package com.linzd.backsystem.core.dockinginterface.controller;


import com.linzd.backsystem.annotation.CheckToken;
import com.linzd.backsystem.annotation.PassToken;
import com.linzd.backsystem.core.dockinginterface.service.DockingInterfaceService;
import com.linzd.backsystem.core.user.entity.Organization;
import com.linzd.backsystem.core.user.entity.Resources;
import com.linzd.backsystem.core.user.entity.Role;
import com.linzd.backsystem.core.user.entity.User;
import com.linzd.backsystem.utils.ResultUtil;
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

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 对外接口表 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-08-20
 */
@RestController
@Api(value = "对外接口表控制层", tags = "对外接口表控制层")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/dockinginterface/dockingInterface")
public class DockingInterfaceController {
    @Autowired
    private DockingInterfaceService  service;

    @ApiOperation(value = "获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "登录名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PassToken
    @PostMapping(value = "/getToken")
    public ResultUtil getToken(@RequestParam("name") String name, @RequestParam("password") String password) {
        return service.getToken(name,password);
    }


    @ApiOperation(value = "获取token")
    @PostMapping(value = "/getUserInfoByToken")
    public ResultUtil getUserInfoByToken(){
        return service.getUserInfoByToken();
    }

    @ApiOperation(value = "获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getUserList")
    public ResultUtil getUserList(@RequestParam Map<String, Object> condition) {
        return service.getUserList(condition);
    }


    @ApiOperation(value = "编辑用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "User")
    })
    @PostMapping(value = "/editUser")
    public ResultUtil editUser(User user) {
       return  service.editUser(user);
    }

    @ApiOperation(value = "获取组织列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getOrganizationList")
    public ResultUtil getOrganizationList(@RequestParam Map<String, Object> condition) {
        return service.getOrganizationList(condition);
    }


    @ApiOperation(value = "编辑组织机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organization", value = "组织机构", required = true, dataType = "Organization")
    })
    @PostMapping(value = "/editOrganization")
    public ResultUtil editOrganization(Organization organization) {
        return  service.editOrganization(organization);

    }


    @ApiOperation(value = "获取菜单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getResourcesList")
    public ResultUtil getResourcesList(@RequestParam Map<String, Object> condition) {
        return service.getResourcesList(condition);
    }

    @ApiOperation(value = "编辑菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resources", value = "菜单", required = true, dataType = "Resources")
    })
    @PostMapping(value = "/editResources")
    public ResultUtil editResources(Resources resources) {
        return  service.editResources(resources);
    }

    @ApiOperation(value = "获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getRoleList")
    public ResultUtil getRoleList(@RequestParam Map<String, Object> condition) {
        return service.getRoleList(condition);
    }


    @ApiOperation(value = "编辑角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "角色", required = true, dataType = "Role")
    })
    @PostMapping(value = "/editRole")
    public ResultUtil editRole(Role role){
        return  service.editRole(role);

    }

    @ApiOperation(value = "获取资源列表根据角色id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getResourcesListByRoleId")
    public ResultUtil getResourcesListByRoleId(@RequestParam Map<String,Object> condition) {
        return service.getResourcesListByRoleId(condition);
    }

    @ApiOperation(value = "修改RoleResources表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", value = "角色id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "addArr", value = "新增的资源数组", required = true, dataType = "List"),
            @ApiImplicitParam(name = "delArr", value = "要删除的资源数组", required = true, dataType = "List")
    })
    @PostMapping(value = "/updateRoleResourcesByRoleId")
    public ResultUtil updateRoleResourcesByRoleId(Long roleid, @RequestParam("addArr") List<Long> addArr, @RequestParam("delArr") List<Long> delArr){
        return service.updateRoleResourcesByRoleId( roleid,   addArr,  delArr);
    }


    @ApiOperation(value = "获取这个角色下的用户列表(全部)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getUserListByRoleId")
    public ResultUtil getUserListByRoleId(@RequestParam Map<String, Object> condition){
        return service.getUserListByRoleId(condition);
    }

    @ApiOperation(value = "修改RoleUser表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", value = "角色id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "addArr", value = "新增的用户数组", required = true, dataType = "List"),
            @ApiImplicitParam(name = "delArr", value = "要删除的用户数组", required = true, dataType = "List")
    })
    @PostMapping(value = "/updateRoleUserByRoleId")
    public ResultUtil updateRoleUserByRoleId(Long roleid, @RequestParam("addArr") List<Long> addArr, @RequestParam("delArr") List<Long> delArr){
        return service.updateRoleUserByRoleId( roleid,   addArr,  delArr);

    }

    @ApiOperation(value = "获取这个用户下的角色列表(全部)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getRoleListByUserId")
    public ResultUtil getRoleListByUserId(@RequestParam Map<String, Object> condition){
        return service.getRoleListByUserId(condition);
    }


    @ApiOperation(value = "修改RoleUser表通过用户id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "addArr", value = "新增的用户数组", required = true, dataType = "List"),
            @ApiImplicitParam(name = "delArr", value = "要删除的用户数组", required = true, dataType = "List")
    })
    @PostMapping(value = "/updateRoleUserByUserId")
    public ResultUtil updateRoleUserByUserId(Long userid, @RequestParam("addArr") List<Long> addArr, @RequestParam("delArr") List<Long> delArr){
        return service.updateRoleUserByUserId( userid,   addArr,  delArr);
    }

}

