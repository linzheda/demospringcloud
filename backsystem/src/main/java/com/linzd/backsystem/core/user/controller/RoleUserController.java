package com.linzd.backsystem.core.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.common.enums.OperType;
import com.linzd.backsystem.core.user.entity.RoleUser;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
@Api(value = "角色用户管理", tags = "角色用户控制层")
@Transactional(rollbackFor=Exception.class)
@CheckToken
@RestController
@RequestMapping("/user/roleUser")
public class RoleUserController {

    @Autowired
    private RoleUserService service;


    @ApiOperation(value = "获取这个角色下的用户列表(全部)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getUserListByRoleId")
    public ResultPojo getUserListByRoleId(@RequestParam Map<String, Object> condition){
        return service.getUserListByRoleId(condition);
    }

    @ApiOperation(value = "获取用户列表(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getUserListByCondition")
    public ResultPojo getUserListByCondition(@RequestParam Map<String, Object> condition){
        return service.getUserListByCondition(condition);
    }



    @ApiOperation(value = "分配用户(根据角色)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", value = "角色id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "addArr", value = "新增的用户数组", required = true, dataType = "List"),
            @ApiImplicitParam(name = "delArr", value = "要删除的用户数组", required = true, dataType = "List")
    })
    @PostMapping(value = "/updateRoleUserByRoleId")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo updateRoleUserByRoleId(Long roleid, @RequestParam("addArr") List<Long> addArr, @RequestParam("delArr") List<Long> delArr){
        //删除
        if(!delArr.isEmpty()){
            QueryWrapper<RoleUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("roleid",roleid);
            queryWrapper.in("userid",delArr);
            service.remove(queryWrapper);
        }
        //新增
        if(!addArr.isEmpty()){
            List<RoleUser> addlist = new ArrayList<>();
            for(Long userid:addArr){
                RoleUser ru=new RoleUser();
                ru.setRoleid(roleid);
                ru.setUserid(userid);
                addlist.add(ru);
            }
            service.saveBatch(addlist);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", true);
        return ResultPojo.success("角色用户分配成功",result);
    }



    @ApiOperation(value = "获取这个用户下的角色列表(全部)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getRoleListByUserId")
    public ResultPojo getRoleListByUserId(@RequestParam Map<String, Object> condition){
        return service.getRoleListByUserId(condition);
    }


    @ApiOperation(value = "获取角色列表(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getRoleListByCondition")
    public ResultPojo getRoleListByCondition(@RequestParam Map<String, Object> condition){
        return service.getRoleListByCondition(condition);
    }



    @ApiOperation(value = "分配角色(根据用户)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "addArr", value = "新增的用户数组", required = true, dataType = "List"),
            @ApiImplicitParam(name = "delArr", value = "要删除的用户数组", required = true, dataType = "List")
    })
    @PostMapping(value = "/updateRoleUserByUserId")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo updateRoleUserByUserId(Long userid, @RequestParam("addArr") List<Long> addArr, @RequestParam("delArr") List<Long> delArr){
        //删除
        if(!delArr.isEmpty()){
            QueryWrapper<RoleUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userid",userid);
            queryWrapper.in("roleid",delArr);
            service.remove(queryWrapper);
        }
        //新增
        if(!addArr.isEmpty()){
            List<RoleUser> addlist = new ArrayList<>();
            for(Long roleid:addArr){
                RoleUser ru=new RoleUser();
                ru.setRoleid(roleid);
                ru.setUserid(userid);
                addlist.add(ru);
            }
            service.saveBatch(addlist);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", true);
        return ResultPojo.success("用户角色分配成功",result);
    }


}

