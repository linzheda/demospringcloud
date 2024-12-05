package com.linzd.backsystem.core.user.controller;


import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.common.enums.OperType;
import com.linzd.backsystem.core.user.entity.Organization;
import com.linzd.backsystem.core.user.service.OrganizationService;
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
 * 机构 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-07-25
 */
@Api(value = "组织机构管理", tags = "机构控制层")
@RestController
@Transactional(rollbackFor = Exception.class)
@CheckToken
@RequestMapping("/user/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService service;


    @ApiOperation(value = "获取组织机构通过pid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "父级pid和过滤某个特定的id", required = true, dataType = "Map")
    })
    @PostMapping(value = "/getOrganizationByPid")
    public ResultPojo getOrganizationByPid(@RequestParam Map<String, Object> condition) {
        return service.getOrganizationByPid(condition);
    }

    @ApiOperation(value = "编辑组织机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organization", value = "组织机构", required = true, dataType = "Organization")
    })
    @PostMapping(value = "/editOrganization")
    @OperLog(type = OperType.UPDATE)
    public ResultPojo editOrganization(Organization organization) {
        String msg =  organization.getId() != null  ? "编辑" : "新增";
        organization.setUpdatetime(null);
        organization.setUpdateby(null);
        boolean isSuccess = organization.insertOrUpdate();
        msg += isSuccess ? "成功" : "失败";
        if(organization.getPid()!=null&&organization.getPid()!=0){
            //说明有父级
            Organization p=new Organization().selectById(organization.getPid());
            organization.setIsn(p.getIsn() + "." + organization.getId());
            organization.setLevel(p.getLevel() + 1);
        }else{
            //说明没有父级
            organization.setPid(0L);
            organization.setLevel(1);
            organization.setIsn( "0." + organization.getId());
        }
        organization.updateById();
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("id", organization.getId());
        return ResultPojo.success(msg, result);
    }


    @ApiOperation(value = "删除机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "机构id", required = true, dataType = "Long")
    })
    @PostMapping(value = "/delOrganization")
    @OperLog(type = OperType.DELETE)
    public ResultPojo delOrganization(Long id) {
        return service.delOrganization(id);
    }





}

