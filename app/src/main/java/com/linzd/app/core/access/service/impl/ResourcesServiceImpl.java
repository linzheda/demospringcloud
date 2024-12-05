package com.linzd.app.core.access.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.linzd.app.core.access.entity.Resources;
import com.linzd.app.core.access.mapper.ResourcesMapper;
import com.linzd.app.core.access.service.ResourcesService;
import com.linzd.basecore.utils.TreeUtil;
import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.basecore.common.entity.RouteTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements ResourcesService {


    @Autowired
    private ResourcesMapper mapper;


    /**
     * 描述  获取权限菜单通过用户id
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/27 10:02
     */
    @Override
    public ResultPojo getResourcesByUserId(Map<String, Object> condition) {
        List<Map<String,Object>> menu=mapper.getResourcesByUserId(condition);
        List<RouteTree> route = new ArrayList<>();
        Gson gson=new Gson();
        for(Map item:menu){
            RouteTree temp=null;
            if(item.get("route")!=null&&!((String)item.get("route")).isEmpty()){
                temp = gson.fromJson(item.get("route").toString(), RouteTree.class);
            }else{
                temp=new RouteTree();
            }
            temp.setId((Long)item.get("id"));
            temp.setPid((Long)item.get("pid"));
            temp.setText(item.get("text").toString());
            if(item.get("btncode")!=null){
                //说明有按钮权限 存入meta
                Map<String,Object> meta= temp.getMeta();
                meta.put("btnPermissions",item.get("btncode"));
                temp.setMeta(meta);
            }
            route.add(temp);
        }
        return ResultPojo.success(route);
    }

    /**
     * 描述  获取菜单路由 下一级
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/27 10:02
     */
    @Override
    public ResultPojo getAccessRoute(Map<String, Object> condition) {
        List<Map<String,Object>> menu=mapper.getAccessRoute(condition);
        List<RouteTree> route = new ArrayList<>();
        Gson gson=new Gson();
        for(Map item:menu){
            RouteTree temp=null;
            if(item.get("route")!=null&&!((String)item.get("route")).isEmpty()){
                temp = gson.fromJson(item.get("route").toString(), RouteTree.class);
            }else{
                temp=new RouteTree();
            }
            temp.setId((Long)item.get("id"));
            temp.setPid((Long)item.get("pid"));
            temp.setText(item.get("text").toString());
            if(item.get("btncode")!=null){
                //说明有按钮权限 存入meta
                Map<String,Object> meta= temp.getMeta();
                meta.put("btnPermissions",item.get("btncode"));
                temp.setMeta(meta);
            }
            route.add(temp);
        }
        TreeUtil tu=new TreeUtil();
        List<RouteTree> tree= tu.toRouteTree(route,Long.valueOf(condition.get("pid").toString()));
        return ResultPojo.success(tree);
    }
}
