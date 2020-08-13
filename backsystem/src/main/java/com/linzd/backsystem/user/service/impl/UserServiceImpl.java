package com.linzd.backsystem.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.user.entity.User;
import com.linzd.backsystem.user.mapper.UserMapper;
import com.linzd.backsystem.user.service.UserService;
import com.linzd.backsystem.utils.Encrypt;
import com.linzd.backsystem.utils.JwtTokenUtil;
import com.linzd.backsystem.utils.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-01-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper mapper;

    /**
     * 描述  登录
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/1/15 20:53
     **/
    @Override
    public ResultUtil login(String name, String password) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            return ResultUtil.error("用户名或密码不允许为空");
        }
        String md5Password = password;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginname", name);
        queryWrapper.eq("password", md5Password);
        User user = null;
        try {
            user = mapper.selectOne(queryWrapper);
            String token = JwtTokenUtil.sign(user.getId());
            user.setToken(token);
        } catch (Exception e) {
            user = null;
            return ResultUtil.error(e.toString());
        }
        if (user != null) {
            return ResultUtil.success(user);
        } else {
            return ResultUtil.error("用户名或密码错误..");
        }

    }

    /**
     * 描述  修改密码
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/4/10 17:02
     **/
    @Override
    public ResultUtil updatePassword(Long id, String oldPassword, String newPassword) {
        User user = mapper.selectById(id);
        String oldPwdMd5 = Encrypt.md5(oldPassword);
        boolean isSuccess=false;
        String msg="";
        if (!user.getPassword().equals(oldPwdMd5)) {
            isSuccess=false;
            msg="原密码错误";
        }else{
            isSuccess=true;
            String newPwdMd5 = Encrypt.md5(newPassword);
            user.setPassword(newPwdMd5);
            user.updateById();
            isSuccess=true;
            msg="修改密码成功,请重新登录";
        }

        return ResultUtil.success(msg, isSuccess);
    }

    /**
     * 描述  获取用户列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/25 18:08
     **/
    @Override
    public ResultUtil getUserList(Map<String, Object> condition) {
        long current= Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current,size);
        IPage<Map> result=mapper.getUserList(page,condition);
        return ResultUtil.success(result);
    }
}
