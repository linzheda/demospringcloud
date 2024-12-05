package com.linzd.basecore.config;

import com.linzd.basecore.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 描述 拦截器配置
 *
 * @author Lorenzo Lin
 * @created 2019年10月11日 14:29
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @CheckToken 注解 决定是否需要登录
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**");
    }

}
