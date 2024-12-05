package com.linzd.basecore.aop;

import com.google.gson.Gson;
import com.linzd.basecore.annotation.OperLog;
import com.linzd.basecore.common.entity.SysLog;
import com.linzd.basecore.common.enums.OperStatus;
import com.linzd.basecore.utils.IpUtil;
import com.linzd.basecore.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述 操作日志切面
 *
 * @author Lorenzo Lin
 * @created 2020年09月10日 20:27
 */
@Component
@Aspect
@Slf4j
public class OperLogAspect {
    /**
     * 排除敏感属性字段
     */
    public static final String[] EXCLUDE_PROPERTIES = {"password", "oldPassword", "newPassword", "confirmPassword"};

    @Autowired
    private HttpServletRequest request;



    // 配置织入点
    @Pointcut("@annotation(com.linzd.basecore.annotation.OperLog)")
    public void logPointCut() {
    }


    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            // 获得注解
            OperLog controllerLog = getAnnotationLog(joinPoint,OperLog.class,false);
            if (controllerLog == null) {
                return;
            }

            // 获取当前的用户
            Long currentUser = JwtTokenUtil.getUserIdByToken(request.getHeader("Authorization"));

            // *========数据库日志=========*//
            SysLog operLog = new SysLog();
            //操作人
            operLog.setOperatorid(currentUser);
            //处理clientid
            if(StringUtils.isNotBlank(request.getHeader("client_id"))){
                operLog.setClientId(request.getHeader("client_id"));
            }
            //默认请求成功
            operLog.setStatus(OperStatus.SUCCESS.ordinal());
            // 请求的地址
            String ip = IpUtil.getIpAddr(request);
            operLog.setIpaddr(ip);
            // 返回结果
            Gson gson=new Gson();
            operLog.setOutresult(gson.toJson(jsonResult));
            //请求的url
            operLog.setRequrl(request.getRequestURI());
            //处理异常
            if (e != null) {
                operLog.setStatus(OperStatus.FAIL.ordinal());
                operLog.setErrormsg(e.getMessage());
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setReqmethod(className + "." + methodName + "()");
            // 设置请求方式
            operLog.setReqtype(request.getMethod());
            //设置参数
            setRequestValue(operLog);
            // 处理设置注解上的参数
            getControllerMethodDescription(controllerLog, operLog,joinPoint);
            // 保存数据库
            operLog.insert();
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }


    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log     日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(OperLog log, SysLog operLog,JoinPoint joinPoint) throws Exception {
        // 设置操作类型
        operLog.setOpertype(log.type().ordinal());
        // 设置系统模块
        if(log.modul().trim().isEmpty()){
            Api api = getAnnotationLog(joinPoint, Api.class, true);
            operLog.setModule(api.value());
        }else{
            operLog.setModule(log.modul());
        }
        // 设置操作说明
        if(log.desc().trim().isEmpty()){
            //ApiOperation
            ApiOperation apiOperation = getAnnotationLog(joinPoint, ApiOperation.class, false);
            operLog.setOperdesc(apiOperation.value());
        }else{
            operLog.setOperdesc(log.desc());
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(SysLog operLog) throws Exception {
        Map<String, String[]> map =request.getParameterMap();
        Map<String, String[]> map1 =new HashMap<>();
        if (!map.isEmpty()) {
            //遍历排查需要过滤的字段
            for (String key : map.keySet()) {
                if(Arrays.asList(EXCLUDE_PROPERTIES).contains(key)){
                    map1.put(key, null);
                }else{
                    map1.put(key, map.get(key));
                }
            }
            Gson  gson=new Gson();
            String params = gson.toJson(map1);
            operLog.setReqparam(params);
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private <T> T getAnnotationLog(JoinPoint joinPoint, Class<? extends Annotation> clazz,boolean isClass) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            if(!isClass){
                return (T) method.getAnnotation(clazz);
            }else{
                return (T) method.getDeclaringClass().getAnnotation(clazz);
            }
        }
        return null;
    }

}
