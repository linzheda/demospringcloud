package com.linzd.backsystem.pub.service.impl;


import com.linzd.backsystem.pub.service.PubService;
import com.linzd.backsystem.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
}
