package com.linzd.backsystem.core.syslog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.common.enums.OperType;
import com.linzd.backsystem.core.syslog.service.SysLogService;
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

import java.util.Map;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-09-11
 */
@RestController
@Api(value = "日志管理", tags = "系统日志控制层")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/syslog/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService service;

    @ApiOperation(value = "获取日志分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getLogList")
    public ResultPojo getLogList(@RequestParam Map<String, Object> condition) {
        return service.getLogList(condition);
    }


    @ApiOperation(value = "删除日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
    })
    @PostMapping(value = "/delLog")
    @OperLog(type = OperType.DELETE)
    public ResultPojo delLog(Long id){
        boolean isSuccess=service.removeById(id);
        String msg =isSuccess ? "删除成功" : "删除失败";
        return ResultPojo.success(msg,isSuccess);
    }


    @ApiOperation(value = "清空日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "日志类型", required = true, dataType = "String")
    })
    @PostMapping(value = "/cleanLog")
    @OperLog(type = OperType.DELETE)
    public ResultPojo cleanLog(String type){
        QueryWrapper queryWrapper = new QueryWrapper();
        switch (type){
            case "oper":
                queryWrapper.ne("opertype",5);
                queryWrapper.isNull("client_id");
                queryWrapper.isNull("errormsg");
                break;
            case "interface":
                queryWrapper.isNotNull("client_id");
                break;
            case "login":
                queryWrapper.eq("opertype", 5);
                queryWrapper.isNull("client_id");
                break;
            case "error":
                queryWrapper.isNotNull("errormsg");
                break;
            default:break;
        }
        boolean isSuccess=service.remove(queryWrapper);
        String msg =isSuccess ? "数据成功清空" : "操作失败";
        return ResultPojo.success(msg,isSuccess);
    }

    @ApiOperation(value = "获取日志时间线")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getLogTimeLine")
    public ResultPojo getLogTimeLine(@RequestParam Map<String, Object> condition) {
        return service.getLogTimeLine(condition);
    }
}

