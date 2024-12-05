package com.linzd.backsystem.core.user.controller;


import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.common.enums.OperType;
import com.linzd.backsystem.core.user.entity.Job;
import com.linzd.backsystem.core.user.service.JobService;
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
 * 岗位 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-08-18
 */
@RestController
@Api(value = "岗位管理", tags = "岗位控制层")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/user/job")
public class JobController {

    @Autowired
    private JobService service;


    @ApiOperation(value = "获取岗位列表(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "条件", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getJobList")
    public ResultPojo getJobList(@RequestParam Map<String, Object> condition) {
        return service.getJobList(condition);
    }

    @ApiOperation(value = "编辑岗位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "job", value = "岗位", required = true, dataType = "Job")
    })
    @PostMapping(value = "/editJob")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo editJob(Job job) {
        boolean isInsert=job.getId() != null ? false:true;
        String msg = isInsert ? "新增" : "编辑";
        job.setUpdateby(null);
        job.setUpdatetime(null);
        boolean isSuccess =job.insertOrUpdate();
        msg += isSuccess ? "成功" : "失败";
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("id", job.getId());
        return ResultPojo.success(msg, result);
    }


    @ApiOperation(value = "删除岗位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "岗位id", required = true, dataType = "Long")
    })
    @PostMapping(value = "/delJob")
    @OperLog(type = OperType.DELETE)
    public ResultPojo delJob(Long id){
        boolean isSuccess=service.removeById(id);
        String msg =isSuccess ? "删除成功" : "删除失败";
        return ResultPojo.success(msg,isSuccess);
    }

}

