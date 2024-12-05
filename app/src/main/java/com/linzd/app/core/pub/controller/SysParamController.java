package com.linzd.app.core.pub.controller;


import com.linzd.basecore.annotation.CheckToken;
import io.swagger.annotations.Api;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统参数 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-09-25
 */
@RestController
@Api(value = "系统参数控制层", tags = "系统参数控制层")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/pub/sysParam")
public class SysParamController {

}
