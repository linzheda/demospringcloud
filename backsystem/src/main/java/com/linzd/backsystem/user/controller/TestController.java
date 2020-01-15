/*
 * Copyright (c) 2005, 2018, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.linzd.backsystem.user.controller;

import com.linzd.backsystem.annotation.PassToken;
import com.linzd.backsystem.feigns.TestFeignClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述
 *
 * @author Lorenzo Lin
 * @created 2020年01月15日 21:23
 */
@Api(value = "测试控制层",tags = "测试控制层")
@RestController
@RequestMapping("/user/testCtr")
public class TestController {

    @Autowired
    private TestFeignClient testFeignClient;
    @PassToken
    @GetMapping("/test")
    public @ResponseBody
    Object test() {
        System.out.println("调用test服务的接口");
        return this.testFeignClient.test(1);
    }

    @PassToken
    @GetMapping("/test2/{id}")
    public @ResponseBody
    Object test2(@PathVariable Integer id) {
        System.out.println("调用test服务的接口");
        return this.testFeignClient.test2(id);
    }
}




