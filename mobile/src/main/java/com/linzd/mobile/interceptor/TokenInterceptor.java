package com.linzd.mobile.interceptor;

import com.linzd.mobile.annotation.PassToken;
import com.linzd.mobile.annotation.UserLoginToken;
import com.linzd.mobile.entity.User;
import com.linzd.mobile.service.UserService;
import com.linzd.mobile.util.JwtTokenUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 描述 token拦截器
 *
 * @author Lorenzo Lin
 * @created 2019年07月11日 20:53
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // OPTIONS请求类型直接返回不处理
        if ("OPTIONS".equals(httpServletRequest.getMethod())){
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
        boolean hasClassUserLoginToken = method.getDeclaringClass().isAnnotationPresent(UserLoginToken.class);
        boolean hasMethodPassToken = method.isAnnotationPresent(PassToken.class);
        boolean hasMethodUserLoginToken = method.isAnnotationPresent(UserLoginToken.class);

        //检查是否有PassToken注释(方法上)，有则跳过认证
        if (hasMethodPassToken) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //是否检查 userLoginToken
        boolean checkFlag = false;
        //获取 UserLoginToken
        UserLoginToken userLoginToken = null;
        //检查方法上是否有UserLoginToken注解
        if (hasMethodUserLoginToken) {
            userLoginToken = method.getAnnotation(UserLoginToken.class);
            checkFlag = true;
        }

        //检查类上有PassToken
        if (hasClassPassToken) {
            PassToken passToken = method.getDeclaringClass().getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //检查类上是否有UserLoginToken注解
        if (hasClassUserLoginToken) {
            userLoginToken = method.getDeclaringClass().getAnnotation(UserLoginToken.class);
            checkFlag = true;
        }

        //统一  要登录验证的  检查
        if (checkFlag) {
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    httpServletResponse.sendError(401, "无token，请重新登录!");
                    return false;
                }
                // 获取 token 中的 user id
                Long userId;
                User user = null;
                try {
                    JwtTokenUtil jtu = new JwtTokenUtil();
                    userId = JwtTokenUtil.verify(token);
                    user = userService.findById(Integer.valueOf(userId.toString()));
                } catch (Exception j) {
                    httpServletResponse.sendError(403, "token错误!");
                    return false;
                }

                if (user == null) {
                    httpServletResponse.sendError(403, "用户不存在!");
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
