package com.linzd.hystrixturbine.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 描述 去掉小尾巴
 *
 * @author Lorenzo Lin
 * @created 2019年12月24日 11:06
 */
@Controller
public class TurbineIndexController {
    @GetMapping("")
    public String index() {
        return "forward:/turbine.stream";
    }
}
