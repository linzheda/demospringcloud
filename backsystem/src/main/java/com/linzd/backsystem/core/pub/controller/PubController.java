package com.linzd.backsystem.core.pub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linzd.backsystem.annotation.PassToken;
import com.linzd.backsystem.annotation.CheckToken;
import com.linzd.backsystem.core.dictionary.entity.Dictionary;
import com.linzd.backsystem.core.pub.service.PubService;
import com.linzd.backsystem.core.user.entity.User;
import com.linzd.backsystem.utils.JwtTokenUtil;
import com.linzd.backsystem.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 描述 通用方法的控制层
 *
 * @author Lorenzo Lin
 * @created 2020年05月27日 15:50
 */
@Api(value = "通用方法的控制层", tags = "通用方法的控制层")
@RestController
@CheckToken
@RequestMapping("/pub/pubCtr")
public class PubController {
    @Autowired
    private PubService pubService;

    @ApiOperation(value = "获取字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "字典key", required = true, dataType = "String"),
            @ApiImplicitParam(name = "level", value = "字典等级", required = true, dataType = "Integer"),
    })
    @PostMapping(value = "/getDict")
    @PassToken
    public ResultUtil getDict(String key, Integer level) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<Dictionary>();
        queryWrapper.eq("dictkey", key);
        if (level != null) {
            queryWrapper.eq("level", level);
        } else {
            queryWrapper.eq("level", 2);
        }
        List<Dictionary> result = new Dictionary().selectList(queryWrapper);
        return ResultUtil.success(result);
    }


    @ApiOperation(value = "根据token获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String")
    })
    @PostMapping(value = "/getUserInfoByToken")
    @PassToken
    public ResultUtil getUserInfoByToken(String token) {
        Map<String, Object> verifyResult = JwtTokenUtil.verify(token);
        Long userId = (long) verifyResult.get("userId");
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("id", userId);
        User user = new User().selectOne(queryWrapper);
        return ResultUtil.success(user);
    }




}
