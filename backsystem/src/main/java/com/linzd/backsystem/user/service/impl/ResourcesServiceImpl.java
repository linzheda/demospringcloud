package com.linzd.backsystem.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.linzd.backsystem.common.entity.RouteTree;
import com.linzd.backsystem.common.entity.Tree;
import com.linzd.backsystem.user.entity.Resources;
import com.linzd.backsystem.user.mapper.ResourcesMapper;
import com.linzd.backsystem.user.service.ResourcesService;
import com.linzd.backsystem.utils.ResultUtil;
import com.linzd.backsystem.utils.TreeUtils;
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

    /**
     * 描述  获取资源菜单通用过用户id
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/3/23 11:24
     **/
    @Override
    public ResultUtil getResourcesByUserId(Map<String,Object> condition) {
        List<Tree> menu=resourcesMapper.getResourcesByUserId(condition);
        List<RouteTree> route = new ArrayList<>();
        Gson gson=new Gson();
        for(Tree<Resources> item:menu){
            RouteTree temp=null;
            if(item.getAttr().getRoute()!=null){
                temp = gson.fromJson(item.getAttr().getRoute(), RouteTree.class);
            }else{
                temp=new RouteTree();
            }
            temp.setId(item.getId());
            temp.setPid(item.getPid());
            temp.setText(item.getText());
            route.add(temp);
        }
        TreeUtils tu=new TreeUtils();
        List<RouteTree> tree= tu.toRouteTree(route,Integer.valueOf(condition.get("pid").toString()));
        return ResultUtil.success(tree);
    }

    /**
     * 描述  根据父级id获取资源菜单列表
     *
     * @author Lorenzo Lin
     * @params  
     * @created 2020/5/26 11:52
     **/
    @Override
    public ResultUtil getResourcesByPid(Long pid) {
        List<Resources> resources = resourcesMapper.getResourcesByPid(pid);
        return ResultUtil.success(resources);
    }
}
