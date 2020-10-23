package com.linzd.app.core.access.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.app.common.entity.ResultPojo;
import com.linzd.app.core.access.entity.User;
import com.linzd.app.core.access.mapper.UserMapper;
import com.linzd.app.core.access.service.UserService;
import com.linzd.app.core.pub.entity.SysParam;
import com.linzd.app.core.pub.service.PubService;
import com.linzd.app.utils.EncryptUtil;
import com.linzd.app.utils.JwtTokenUtil;
import com.linzd.app.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private PubService pubService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 描述  登录
     *
     * @param name
     * @param password
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/24 15:34
     */
    @Override
    public ResultPojo login(String name, String password) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            return ResultPojo.error("用户名或密码不允许为空");
        }
        String md5Password = password;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginname", name);
        //查询超级密码
        SysParam superPassword = pubService.getSysParam("superPassword");
        String superPasswordText = superPassword.getValue();
        if (!md5Password.equals(EncryptUtil.md5(superPasswordText))) {
            //说明不是超级密码
            queryWrapper.eq("password", md5Password);
        }
        User user = mapper.selectOne(queryWrapper);
        if (user != null) {
            if (user.getStatus() == 1) {
                //查询登录策略
                SysParam loginType = pubService.getSysParam("loginType");
                String token = null;
                String key = JwtTokenUtil.LOGIN_TOKEN_PREFIX + user.getId();
                if (loginType.getValue().equals("1")) {
                    //允许同时在线
                    if (redisUtil.hasKey(key)) {
                        //如果键存在则取出 token
                        token = redisUtil.get(key).toString();
                    } else {
                        //如果不存在则生成新的token
                        token = JwtTokenUtil.sign(user.getId());
                        redisUtil.set(key, token, JwtTokenUtil.EXPIRE_TIME, TimeUnit.MILLISECONDS);
                    }
                } else {
                    //不允许同时在线生成新的token
                    token = JwtTokenUtil.sign(user.getId());
                    redisUtil.set(key, token, JwtTokenUtil.EXPIRE_TIME, TimeUnit.MILLISECONDS);
                }
                user.setToken(token);
                return ResultPojo.success(user);
            } else {
                return ResultPojo.error("账户被冻结,请联系管理员..");
            }
        } else {
            return ResultPojo.error("用户名或密码错误..");
        }
    }

    /**
     * 描述  修改密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/24 15:34
     */
    @Override
    public ResultPojo updatePassword(Long id, String oldPassword, String newPassword) {
        User user = mapper.selectById(id);
        String oldPwdMd5 = EncryptUtil.md5(oldPassword);
        boolean isSuccess = false;
        String msg = "";
        if (!user.getPassword().equals(oldPwdMd5)) {
            isSuccess = false;
            msg = "原密码错误";
        } else {
            isSuccess = true;
            String newPwdMd5 = EncryptUtil.md5(newPassword);
            user.setPassword(newPwdMd5);
            user.updateById();
            isSuccess = true;
            msg = "修改密码成功,请重新登录";
        }

        return ResultPojo.success(msg, isSuccess);
    }

    /**
     * 描述  登出
     *
     * @param id
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/21 16:08
     */
    @Override
    public ResultPojo loginOut(Long id) {
        String key = JwtTokenUtil.LOGIN_TOKEN_PREFIX + id;
        if (redisUtil.hasKey(key)) {
            redisUtil.del(key);
        }
        return ResultPojo.success("登出成功");
    }

    /**
     * 描述  发送验证码
     *
     * @param tel
     * @author Lorenzo Lin
     * @params
     * @created 2020/10/15 14:46
     */
    @Override
    public ResultPojo sendSms(String tel) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel", tel);
        User user = new User().selectOne(queryWrapper);
        if (user != null) {
            if (user.getSmstime() != null) {
                Duration duration = Duration.between(LocalDateTime.now(), user.getSmstime());
                if (duration.toMinutes() < 1) {
                    //说明小于1分钟
                    return ResultPojo.error("一分钟之内只能发送一次验证码！");
                }
            }
            //生成随机验证码
            String sms = String.valueOf((Math.random() * 9 + 1) * 100000);
            user.setSms(sms);
            user.setSmstime(LocalDateTime.now());
            user.updateById();
            //TODO:这里调发送短信的接口
            return ResultPojo.success("发送成功", sms);

        } else {
            return ResultPojo.error("当前用户不存在！");
        }
    }
}
