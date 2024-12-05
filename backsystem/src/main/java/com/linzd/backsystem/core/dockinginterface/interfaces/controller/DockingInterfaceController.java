package com.linzd.backsystem.core.dockinginterface.interfaces.controller;


import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.annotation.PassToken;
import com.linzd.basecore.common.enums.OperType;
import com.linzd.backsystem.core.dockinginterface.interfaces.service.DockingInterfaceService;
import com.linzd.backsystem.core.user.entity.Organization;
import com.linzd.backsystem.core.user.entity.Resources;
import com.linzd.backsystem.core.user.entity.Role;
import com.linzd.backsystem.core.user.entity.User;
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
@Api(value = "对外接口", tags = "对外接口表控制层")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/dockinginterface/dockingInterface")
public class DockingInterfaceController {
    @Autowired
    private DockingInterfaceService service;

    @ApiOperation(value = "获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "登录名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PassToken
    @PostMapping(value = "/getToken")
    @OperLog(type = OperType.SELECT)
    public ResultPojo getToken(@RequestParam("name") String name, @RequestParam("password") String password) {
        return service.getToken(name,password);
    }


    @ApiOperation(value = "根据token获取用户信息")
    @PostMapping(value = "/getUserInfoByToken")
    @OperLog(type = OperType.SELECT)
    public ResultPojo getUserInfoByToken(){
        return service.getUserInfoByToken();
    }

    @ApiOperation(value = "获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getUserList")
    @OperLog(type = OperType.SELECT)
    public ResultPojo getUserList(@RequestParam Map<String, Object> condition) {
        return service.getUserList(condition);
    }


    @ApiOperation(value = "编辑用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "User")
    })
    @PostMapping(value = "/editUser")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo editUser(User user) {
       return  service.editUser(user);
    }

    @ApiOperation(value = "获取组织列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getOrganizationList")
    @OperLog(type = OperType.SELECT)
    public ResultPojo getOrganizationList(@RequestParam Map<String, Object> condition) {
        return service.getOrganizationList(condition);
    }


    @ApiOperation(value = "编辑组织机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organization", value = "组织机构", required = true, dataType = "Organization")
    })
    @PostMapping(value = "/editOrganization")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo editOrganization(Organization organization) {
        return  service.editOrganization(organization);

    }


    @ApiOperation(value = "获取菜单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getResourcesList")
    @OperLog(type = OperType.SELECT)
    public ResultPojo getResourcesList(@RequestParam Map<String, Object> condition) {
        return service.getResourcesList(condition);
    }

    @ApiOperation(value = "编辑菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resources", value = "菜单", required = true, dataType = "Resources")
    })
    @PostMapping(value = "/editResources")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo editResources(Resources resources) {
        return  service.editResources(resources);
    }

    @ApiOperation(value = "获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getRoleList")
    @OperLog(type = OperType.SELECT)
    public ResultPojo getRoleList(@RequestParam Map<String, Object> condition) {
        return service.getRoleList(condition);
    }


    @ApiOperation(value = "编辑角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "角色", required = true, dataType = "Role")
    })
    @PostMapping(value = "/editRole")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo editRole(Role role){
        return  service.editRole(role);

    }

    @ApiOperation(value = "根据角色id获取资源列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getResourcesListByRoleId")
    @OperLog(type = OperType.SELECT)
    public ResultPojo getResourcesListByRoleId(@RequestParam Map<String,Object> condition) {
        return service.getResourcesListByRoleId(condition);
    }

    @ApiOperation(value = "分配角色菜单资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", value = "角色id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "addArr", value = "新增的资源数组", required = true, dataType = "List"),
            @ApiImplicitParam(name = "delArr", value = "要删除的资源数组", required = true, dataType = "List")
    })
    @PostMapping(value = "/updateRoleResourcesByRoleId")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo updateRoleResourcesByRoleId(Long roleid, @RequestParam("addArr") List<Long> addArr, @RequestParam("delArr") List<Long> delArr){
        return service.updateRoleResourcesByRoleId( roleid,   addArr,  delArr);
    }


    @ApiOperation(value = "获取这个角色下的(全部)用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getUserListByRoleId")
    @OperLog(type = OperType.SELECT)
    public ResultPojo getUserListByRoleId(@RequestParam Map<String, Object> condition){
        return service.getUserListByRoleId(condition);
    }

    @ApiOperation(value = "根据角色分配角色用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", value = "角色id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "addArr", value = "新增的用户数组", required = true, dataType = "List"),
            @ApiImplicitParam(name = "delArr", value = "要删除的用户数组", required = true, dataType = "List")
    })
    @PostMapping(value = "/updateRoleUserByRoleId")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo updateRoleUserByRoleId(Long roleid, @RequestParam("addArr") List<Long> addArr, @RequestParam("delArr") List<Long> delArr){
        return service.updateRoleUserByRoleId( roleid,   addArr,  delArr);

    }

    @ApiOperation(value = "获取这个用户下的(全部)角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getRoleListByUserId")
    @OperLog(type = OperType.SELECT)
    public ResultPojo getRoleListByUserId(@RequestParam Map<String, Object> condition){
        return service.getRoleListByUserId(condition);
    }


    @ApiOperation(value = "根据用户分配角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "addArr", value = "新增的用户数组", required = true, dataType = "List"),
            @ApiImplicitParam(name = "delArr", value = "要删除的用户数组", required = true, dataType = "List")
    })
    @PostMapping(value = "/updateRoleUserByUserId")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo updateRoleUserByUserId(Long userid, @RequestParam("addArr") List<Long> addArr, @RequestParam("delArr") List<Long> delArr){
        return service.updateRoleUserByUserId( userid,   addArr,  delArr);
    }

}

