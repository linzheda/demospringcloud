/*
 * Copyright (c) 2005, 2018, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.linzd.hystrixdashboard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 描述  去掉hystrix 的小尾巴
 *
 * @author Lorenzo Lin
 * @created 2019年12月13日 14:21
 */
@Controller
public class HystrixIndexController {
    @GetMapping("")
    public String index() {
        return "forward:/hystrix";
    }
}
