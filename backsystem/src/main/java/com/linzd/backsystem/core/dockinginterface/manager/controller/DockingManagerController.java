package com.linzd.backsystem.core.dockinginterface.manager.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.common.enums.OperType;
import com.linzd.backsystem.core.dockinginterface.manager.entity.DockingInterface;
import com.linzd.backsystem.core.dockinginterface.manager.service.DockingManagerService;
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
 * 描述对外接口管理
 *
 * @author Lorenzo Lin
 * @created 2020年09月16日 9:36
 */
@RestController
@Api(value = "对外接口管理", tags = "对外接口管理")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/dockinginterface/manager")
public class DockingManagerController {

    @Autowired
    private DockingManagerService service;

    @ApiOperation(value = "获取未入库的接口")
    @PostMapping(value = "/getInterfaceUrl")
    public ResultPojo getInterfaceUrl() {
        return service.getInterfaceUrl();
    }

    @ApiOperation(value = "获取所有入库的接口分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getInterfaceList")
    public ResultPojo getInterfaceList(@RequestParam Map<String, Object> condition) {
        return service.getInterfaceList(condition);
    }

    @ApiOperation(value = "新增接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "list", value = "列表", required = true, dataType = "String")
    })
    @PostMapping(value = "/addInterfaceList")
    @OperLog(type = OperType.INSERT)
    public ResultPojo addInterfaceList(@RequestParam  String list) {
        Gson gson=new Gson();
        List<DockingInterface> list1 = gson.fromJson(list,new TypeToken<ArrayList<DockingInterface>>() {}.getType());
        boolean isSuccess = service.saveBatch(list1);
        String msg = isSuccess ? "新增成功" : "新增失败";
        return ResultPojo.success(msg,isSuccess);
    }

    @ApiOperation(value = "删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
    })
    @PostMapping(value = "/delInterface")
    @OperLog(type = OperType.DELETE)
    public ResultPojo delInterface(Long id){
        boolean isSuccess=service.removeById(id);
        String msg =isSuccess ? "删除成功" : "删除失败";
        return ResultPojo.success(msg,isSuccess);
    }


    @ApiOperation(value = "编辑接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entity", value = "接口", required = true, dataType = "DockingInterface")
    })
    @PostMapping(value = "/editInterface")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo editInterface(DockingInterface entity) {
        boolean isInsert= entity.getId() == null;
        String msg = isInsert ? "新增" : "编辑";
        entity.setUpdateby(null);
        entity.setUpdatetime(null);
        boolean isSuccess =entity.insertOrUpdate();
        msg += isSuccess ? "成功" : "失败";
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("id", entity.getId());
        return ResultPojo.success(msg, result);
    }


}
