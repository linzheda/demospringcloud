package com.linzd.app.core.access.controller;


import com.linzd.basecore.annotation.CheckToken;
import io.swagger.annotations.Api;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@RestController
@Api(value = "角色控制层", tags = "角色控制层")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/access/role")
public class RoleController {

}

