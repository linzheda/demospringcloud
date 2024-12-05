package com.linzd.backsystem.core.pub.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linzd.backsystem.core.dictionary.entity.Dictionary;
import com.linzd.backsystem.core.pub.service.PubService;
import com.linzd.backsystem.core.sysparam.entity.SysParam;
import com.linzd.backsystem.core.user.entity.User;
import com.linzd.basecore.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 描述  通用业务层的实现类
 *
 * @author Lorenzo Lin
 * @created 2020年05月27日 15:53
 */
@Service
public class PubServiceimpl  implements PubService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;


    /**
     * 描述  根据token 获取用户id
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/21 10:38
     **/
    @Override
    public Long getUserIdByToken() {
        String token = request.getHeader("Authorization");
        if(StringUtils.isNotBlank(token)){
            Map<String,Object> verifyResult= JwtTokenUtil.verify(token);
            Long  userId = (long)verifyResult.get("userId") ;
            return userId;
        }
        return null;
    }

    /**
     * 描述  根据token获取用户信息
     *
     * @param token
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/21 10:40
     */
    @Override
    public User getUserInfoByToken(String token) {
        Map<String, Object> verifyResult = JwtTokenUtil.verify(token);
        Long userId = (long) verifyResult.get("userId");
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("id", userId);
        User user = new User().selectOne(queryWrapper);
        return user;
    }

    /**
     * 描述 获取字典
     *
     * @param key
     * @param level
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/21 10:36
     */
    @Override
    public List<Dictionary> getDict(String key, Integer level) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<Dictionary>();
        queryWrapper.eq("dictkey", key);
        if (level != null) {
            queryWrapper.eq("level", level);
        } else {
            queryWrapper.eq("level", 2);
        }
        queryWrapper.orderByAsc("seq");
        List<Dictionary> result = new Dictionary().selectList(queryWrapper);
        return result;
    }

    /**
     * 描述  获取系统参数
     *
     * @param code
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/21 10:38
     */
    @Override
    @Cacheable(value = "sysparam")
    public SysParam getSysParam(String code) {
        QueryWrapper<SysParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", code);
        queryWrapper.orderByAsc("seq");
        SysParam result = new SysParam().selectOne(queryWrapper);
        return result;
    }
}
