package com.linzd.test.test.controller;


import com.linzd.test.test.entity.Test;
import com.linzd.test.test.service.TestService;
import com.linzd.test.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linzd
 * @since 2020-01-15
 */
@Api(value = "测试控制层",tags = "测试控制层")
@RestController
@RequestMapping("/test/testCtr")
public class TestController {
    @Autowired
    private TestService testService;

    @ApiOperation(value = "测试接口1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/test")
    public @ResponseBody
    ResultUtil test(Integer id) {
        System.out.println("进来了test");
        Test test = testService.getById(id);
        System.out.println(test);
        return ResultUtil.success(test);
    }

    @ApiOperation(value = "测试接口2")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/test2/{id}")
    public @ResponseBody
    ResultUtil test2(@PathVariable Integer id) {
        System.out.println("进来了test2");
        Test test = testService.getById(id);
        return ResultUtil.success(test);
    }

}

