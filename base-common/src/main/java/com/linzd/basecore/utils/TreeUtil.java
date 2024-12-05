package com.linzd.basecore.utils;

import com.linzd.basecore.common.entity.Tree;
import com.linzd.basecore.common.entity.RouteTree;

import java.util.*;

/**
 * 描述 树级菜单生成工具类
 *
 * @author Lorenzo Lin
 * @created 2020年03月23日 14:34
 */
public class TreeUtil<T> {

    /**
     * 描述  生成普通树
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/3/27 14:19
     **/
    public List<Tree> toTree(List<Tree> menus) {
        List<Tree> result = new ArrayList<>();
        //用递归找子。
        for (Tree tree : menus) {
            if (tree.getPid() == 0) {
                result.add(findChildren(tree, menus));
            }
        }
        return result;
    }

    private Tree findChildren(Tree tree, List<Tree> list) {
        for (Tree node : list) {
            if (node.getPid().equals(tree.getId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<Tree>());
                }
                tree.getChildren().add(findChildren(node, list));
            }
        }
        return tree;
    }

    /**
     * 描述  生成路由树
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/3/27 14:20
     **/
    public List<RouteTree> toRouteTree(List<RouteTree> menus, Long pid) {
        List<RouteTree> result = new ArrayList<>();
        //用递归找子。
        for (RouteTree tree : menus) {
            if (tree.getPid().equals(pid)) {
                result.add(findChildren(tree, menus));
            }
        }
        return result;
    }


    private RouteTree findChildren(RouteTree tree, List<RouteTree> list) {
        for (RouteTree node : list) {
            if (node.getPid().equals(tree.getId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<RouteTree>());
                }
                tree.getChildren().add(findChildren(node, list));
            }
        }
        return tree;
    }


    /**
     * 描述  生产map的树
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/3 10:21
     **/
    public List<Map<String, Object>> toMapTree(List<Map<String, Object>> menus, Long pid) {
        List<Map<String, Object>> result = new ArrayList<>();
        if(pid!=null){
            //用递归找子。
            for (Map<String, Object> tree : menus) {
                if (tree.get("pid").equals(pid)) {
                    result.add(findChildren(tree, menus));
                }
            }
        }else{
            HashSet<Object> pid_set = new HashSet<>();
            for  (Map<String, Object> item : menus) {

                pid_set.add(item.get("id"));
            }
            for  (Map<String, Object> item : menus) {
                //说明不存在
               if(!pid_set.contains(item.get("pid"))){
                   result.add(findChildren(item, menus));
               }
            }
        }
        return result;
    }

    private Map<String, Object> findChildren(Map<String, Object> tree, List<Map<String, Object>> list) {
        for (Map<String, Object> node : list) {
            if (node.get("pid").equals(tree.get("id"))) {
                if (tree.get("children") == null) {
                    tree.put("children", new ArrayList<>());
                }
                List<Map<String, Object>> children =(ArrayList) tree.get("children");
                children.add(findChildren(node, list));
                tree.put("children", children);
            }
        }
        return tree;
    }


}
