package com.linzd.backsystem.utils;

import com.linzd.backsystem.common.entity.RouteTree;
import com.linzd.backsystem.common.entity.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 树级菜单生成工具类
 *
 * @author Lorenzo Lin
 * @created 2020年03月23日 14:34
 */
public class TreeUtils<T> {

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
            if (tree.getPid()==0) {
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
    public List<RouteTree> toRouteTree(List<RouteTree> menus,Integer pid) {
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
}
