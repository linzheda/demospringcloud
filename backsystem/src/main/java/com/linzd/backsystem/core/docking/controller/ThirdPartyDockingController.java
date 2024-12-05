package com.linzd.backsystem.core.docking.controller;


import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.common.enums.OperType;
import com.linzd.backsystem.core.docking.entity.ThirdPartyDocking;
import com.linzd.backsystem.core.docking.service.ThirdPartyDockingService;
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
import java.util.UUID;

/**
 * <p>
 * 第三方平台应用表  前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-08-20
 */
@RestController
@Api(value = "第三方应用管理", tags = "第三方平台应用表 控制层")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/docking/thirdPartyDocking")
public class ThirdPartyDockingController {

    @Autowired
    private ThirdPartyDockingService service;


    @ApiOperation(value = "获取应用列表(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getThirdPartyDockingList")
    public ResultPojo getThirdPartyDockingList(@RequestParam Map<String, Object> condition) {
        return service.getThirdPartyDockingList(condition);
    }

    @ApiOperation(value = "编辑应用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entity", value = "应用", required = true, dataType = "ThirdPartyDocking")
    })
    @PostMapping(value = "/editThirdPartyDocking")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo editThirdPartyDocking(ThirdPartyDocking entity) {
        boolean isInsert=entity.getId() != null ? false:true;
        String msg = isInsert ? "新增" : "编辑";
        entity.setUpdateby(null);
        entity.setUpdatetime(null);
        if(isInsert){
            //新增 生产标识和秘钥
            entity.setClientId(UUID.randomUUID().toString());
            entity.setClientSecret(UUID.randomUUID().toString());
        }
        boolean isSuccess =entity.insertOrUpdate();
        msg += isSuccess ? "成功" : "失败";
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("id", entity.getId());
        return ResultPojo.success(msg, result);
    }


    @ApiOperation(value = "删除应用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "应用id", required = true, dataType = "Long")
    })
    @PostMapping(value = "/delThirdPartyDocking")
    @OperLog(type = OperType.DELETE)
    public ResultPojo delThirdPartyDocking(Long id){
        boolean isSuccess=service.removeById(id);
        String msg =isSuccess ? "删除成功" : "删除失败";
        return ResultPojo.success(msg,isSuccess);
    }

}

