package com.linzd.app.core.pub.controller;


import com.linzd.basecore.annotation.CheckToken;
import io.swagger.annotations.Api;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典 前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-09-25
 */
@RestController
@Api(value = "字典控制层", tags = "字典控制层")
@CheckToken
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/pub/dictionary")
public class DictionaryController {

}

