package com.linzd.basecore.interceptor;


import com.linzd.basecore.utils.JwtTokenUtil;
import com.linzd.basecore.utils.RedisUtil;
import com.linzd.basecore.annotation.CheckToken;
import com.linzd.basecore.annotation.PassToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 描述 token拦截器
 *
 * @author Lorenzo Lin
 * @created 2019年07月11日 20:53
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // OPTIONS请求类型直接返回不处理
        if ("OPTIONS".equals(httpServletRequest.getMethod())) {
            return false;
        }
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("Authorization");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        boolean hasClassPassToken = method.getDeclaringClass().isAnnotationPresent(PassToken.class);
        boolean hasClassCheckToken = method.getDeclaringClass().isAnnotationPresent(CheckToken.class);
        boolean hasMethodPassToken = method.isAnnotationPresent(PassToken.class);
        boolean hasMethodCheckToken = method.isAnnotationPresent(CheckToken.class);

        //检查是否有PassToken注释(方法上)，有则跳过认证
        if (hasMethodPassToken) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //是否检查 CheckToken
        boolean checkFlag = false;
        //获取 CheckToken
        CheckToken CheckToken = null;
        //检查方法上是否有CheckToken注解
        if (hasMethodCheckToken) {
            CheckToken = method.getAnnotation(CheckToken.class);
            checkFlag = true;
        }

        //检查类上有PassToken
        if (hasClassPassToken) {
            PassToken passToken = method.getDeclaringClass().getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //检查类上是否有CheckToken注解
        if (hasClassCheckToken) {
            CheckToken = method.getDeclaringClass().getAnnotation(CheckToken.class);
            checkFlag = true;
        }

        //统一  要登录验证的  检查
        if (checkFlag) {
            if (CheckToken.required()) {
                // 执行认证
                if (token == null) {
                    httpServletResponse.sendError(401, "无token，请重新登录!");
                    return false;
                }
                // 获取 token 中的 user id
                Long userId;
                try {
                    Map<String, Object> verifyResult = JwtTokenUtil.verify(token);
                    userId = (long) verifyResult.get("userId");
                    String key = JwtTokenUtil.LOGIN_TOKEN_PREFIX + userId;
                    //如果可以不存在或者token值不对
                    if (!redisUtil.hasKey(key) || !redisUtil.get(key).equals(token)) {
                        //如果token和redis的对不上这包403
                        httpServletResponse.sendError(403, "token错误!");
                        return false;
                    }
                    long exp = ((Date) verifyResult.get("exp")).getTime();
                    long now = System.currentTimeMillis();
                    //说明当前时间+10分钟小于到期时间=>距离过期时间不足10分钟 生产新的token
                    if ((now + 600000) > exp) {
                        String newToken = JwtTokenUtil.sign(userId);
                        httpServletResponse.setHeader("authorization", newToken);
                        redisUtil.set(key, newToken, JwtTokenUtil.EXPIRE_TIME, TimeUnit.MILLISECONDS);
                    }
                } catch (Exception j) {
                    httpServletResponse.sendError(403, "token错误!");
                    return false;
                }
                return true;
            }
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
