package com.linzd.app.core.access.controller;


import io.swagger.annotations.Api;
import com.linzd.basecore.annotation.CheckToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 岗位 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@RestController
@Api(value = "岗位控制层", tags = "岗位控制层")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/access/job")
public class JobController {

}

