package com.linzd.backsystem.core.serverinfo.controller;

import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.backsystem.core.serverinfo.entity.SystemHardwareInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述 服务器监控
 *
 * @author Lorenzo Lin
 * @created 2020年09月22日 10:34
 */
@Api(value = "服务器监控", tags = "服务器监控")
@RestController
@CheckToken
@RequestMapping("/serverinfo/serverInfoCtr")
public class ServerInfoController {


    @ApiOperation(value = "服务器监控首页", notes = "服务器监控首页")
    @GetMapping("/getInfo")
    public ResultPojo getInfo() throws Exception{
        SystemHardwareInfo server = new SystemHardwareInfo();
        server.copyTo();
        return ResultPojo.success(server);
    }

}
