package com.linzd.backsystem.core.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.common.enums.OperType;
import com.linzd.backsystem.core.user.entity.RoleResources;
import com.linzd.backsystem.core.user.service.RoleResourcesService;
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
@Api(value = "角色资源管理", tags = "角色资源控制层")
@Transactional(rollbackFor=Exception.class)
@CheckToken
@RestController
@RequestMapping("/user/roleResources")
public class RoleResourcesController {
    @Autowired
    private RoleResourcesService service;

    @ApiOperation(value = "获取资源列表根据角色id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getResourcesListByRoleId")
    public ResultPojo getResourcesListByRoleId(@RequestParam Map<String,Object> condition) {
        return service.getResourcesListByRoleId(condition);
    }

    @ApiOperation(value = "分配菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", value = "角色id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "addArr", value = "新增的资源数组", required = true, dataType = "List"),
            @ApiImplicitParam(name = "delArr", value = "要删除的资源数组", required = true, dataType = "List")
    })
    @PostMapping(value = "/updateRoleResourcesByRoleId")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo updateRoleResourcesByRoleId(Long roleid, @RequestParam("addArr") List<Long> addArr, @RequestParam("delArr") List<Long> delArr){
        //删除
        if(!delArr.isEmpty()){
            QueryWrapper<RoleResources> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("roleid",roleid);
            queryWrapper.in("resid",delArr);
            service.remove(queryWrapper);
        }
        //新增
        if(!addArr.isEmpty()){
            List<RoleResources> addlist = new ArrayList<>();
            for(Long resid:addArr){
                RoleResources rr=new RoleResources();
                rr.setRoleid(roleid);
                rr.setResid(resid);
                addlist.add(rr);
            }
            service.saveBatch(addlist);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", true);
        return ResultPojo.success("资源菜单分配成功",result);
    }


}

