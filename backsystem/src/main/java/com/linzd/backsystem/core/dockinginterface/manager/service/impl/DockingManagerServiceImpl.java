package com.linzd.backsystem.core.dockinginterface.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.core.dockinginterface.manager.entity.DockingInterface;
import com.linzd.backsystem.core.dockinginterface.manager.mapper.DockingManagerMapper;
import com.linzd.backsystem.core.dockinginterface.manager.service.DockingManagerService;
import com.linzd.basecore.common.entity.ResultPojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述  对外接口管理业务层实现类
 *
 * @author Lorenzo Lin
 * @created 2020年09月16日 9:40
 */
@Service
public class DockingManagerServiceImpl extends ServiceImpl<DockingManagerMapper, DockingInterface> implements DockingManagerService {
    @Autowired
    private DockingManagerMapper mapper;

    @Autowired
    WebApplicationContext applicationContext;

    /**
     * 描述  获取对外接口
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/16 9:53
     */
    @Override
    public ResultPojo getInterfaceUrl() {
        //获取所有的url
        List<Map<String, String>> allUrl = getAllUrl();
        //过滤出对外的url
        List<Map<String, String>> dockingUrl = new ArrayList<>();
        for (Map<String, String> tempUrl : allUrl) {
            if (tempUrl.get("url").startsWith("/dockinginterface/")
                    && !tempUrl.get("url").startsWith("/dockinginterface/manager")) {
                dockingUrl.add(tempUrl);
            }
        }
        //获取接口列表
        QueryWrapper<DockingInterface> queryWrapper = new QueryWrapper();
        List<DockingInterface> interfaceList = mapper.selectList(queryWrapper);
        //过滤掉已经入库的数据
        for (DockingInterface i : interfaceList) {
            String url = i.getUrl();
            dockingUrl.removeIf(m -> url.equals(m.get("url")));
        }
        return ResultPojo.success(dockingUrl);
    }

    /**
     * 描述  获取所有入库的接口分页列表
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/16 10:15
     */
    @Override
    public ResultPojo getInterfaceList(Map<String, Object> condition) {
        long current = Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        IPage<Map<String, Object>> page = new Page<>(current, size);
        QueryWrapper<DockingInterface> queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc("method");
        if(StringUtils.isNotBlank((String) condition.get("name"))){
            queryWrapper.like("name",condition.get("name"));
        }
        if(StringUtils.isNotBlank((String) condition.get("url"))){
            queryWrapper.like("url",condition.get("url"));
        }
        IPage<Map<String, Object>> result = mapper.selectMapsPage(page, queryWrapper);
        for(Map<String, Object> map:result.getRecords()){
            map.put("status_text", ((int) map.get("status")) == 1 ? "启用" : "禁用");
        }
        return ResultPojo.success(result);
    }


    /**
     * 描述  获取所有的url
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/16 10:50
     **/
    private List<Map<String, String>> getAllUrl() {
        // 获取springmvc处理器映射器组件对象 RequestMappingHandlerMapping无法直接注入
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            Map<String, String> mapone = new HashMap<String, String>();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            // 获取url
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                mapone.put("url", url);
            }
            // 反射获取url对应类名和方法名

            // 方法名
            mapone.put("method", method.getMethod().getName());
            // 类名+方法名
            mapone.put("path", method.getMethod().getDeclaringClass().getName()+"."+mapone.get("method"));
            //获取swagger 的 Api, ApiOperation 注解的值
            ApiOperation apiOperation = method.getMethod().getAnnotation(ApiOperation.class);
            Api api = method.getMethod().getDeclaringClass().getAnnotation(Api.class);
            if (api != null && apiOperation != null) {
                mapone.put("name", api.value() + "=>" + apiOperation.value());
            }

            // 获取请求类型
            RequestMethodsRequestCondition methodsRequestCondition = info.getMethodsCondition();
            for (RequestMethod requestMethod : methodsRequestCondition.getMethods()) {
                mapone.put("type", requestMethod.toString());
            }
            list.add(mapone);
        }
        return list;
    }
}
