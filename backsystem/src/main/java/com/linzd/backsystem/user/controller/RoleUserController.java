package com.linzd.backsystem.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linzd.backsystem.annotation.UserLoginToken;
import com.linzd.backsystem.user.entity.RoleUser;
import com.linzd.backsystem.user.service.RoleUserService;
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
@Api(value = "角色用户控制层", tags = "角色用户控制层")
@Transactional(rollbackFor=Exception.class)
@UserLoginToken
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
    public ResultUtil getUserListByRoleId(@RequestParam Map<String, Object> condition){
        return service.getUserListByRoleId(condition);
    }

    @ApiOperation(value = "获取用户列表(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getUserListByCondition")
    public ResultUtil getUserListByCondition(@RequestParam Map<String, Object> condition){
        return service.getUserListByCondition(condition);
    }

    @ApiOperation(value = "修改RoleUser表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", value = "角色id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "addArr", value = "新增的用户数组", required = true, dataType = "List"),
            @ApiImplicitParam(name = "delArr", value = "要删除的用户数组", required = true, dataType = "List")
    })
    @PostMapping(value = "/updateRoleUserByRoleId")
    public ResultUtil updateRoleUserByRoleId(Long roleid, @RequestParam("addArr") List<Long> addArr, @RequestParam("delArr") List<Long> delArr){
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
        return ResultUtil.success("角色用户分配成功",result);
    }



}

