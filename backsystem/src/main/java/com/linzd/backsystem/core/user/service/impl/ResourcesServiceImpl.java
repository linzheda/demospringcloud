package com.linzd.backsystem.core.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.linzd.basecore.common.entity.RouteTree;
import com.linzd.backsystem.core.user.entity.Resources;
import com.linzd.backsystem.core.user.mapper.ResourcesMapper;
import com.linzd.backsystem.core.user.service.ResourcesService;
import com.linzd.backsystem.core.user.service.RoleResourcesService;
import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.basecore.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类  资源菜单
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements ResourcesService {

    @Autowired
    private ResourcesMapper resourcesMapper;
    @Autowired
    private RoleResourcesService roleResourcesService;

    /**
     * 描述  获取资源菜单通用过用户id
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/3/23 11:24
     **/
    @Override
    public ResultPojo getResourcesByUserId(Map<String,Object> condition) {
        List<Map<String,Object>> menu=resourcesMapper.getResourcesByUserId(condition);
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

    /**
     * 描述  根据父级id获取资源菜单列表
     *
     * @author Lorenzo Lin
     * @params  
     * @created 2020/5/26 11:52
     **/
    @Override
    public ResultPojo getResourcesByPid(Map<String,Object> condition) {
        List<Map<String,Object>> resources = resourcesMapper.getResourcesByPid(condition);
        TreeUtil tu=new TreeUtil();
        List<Map<String,Object>> tree=tu.toMapTree(resources,null);
        return ResultPojo.success(tree);
    }


    /**
     * 描述  删除资源和下级
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/16 16:48
     **/
    @Override
    public ResultPojo delResources(Long id) {
        //删除资源
        int result= resourcesMapper.delResources(id);
        String msg = result >0 ? "删除成功" : "删除失败";
        //删除角色资源的关联数据
        roleResourcesService.delRoleResourcesLink();
        return ResultPojo.success(msg, result);
    }
}
