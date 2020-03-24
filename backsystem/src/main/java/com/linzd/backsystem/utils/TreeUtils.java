package com.linzd.backsystem.utils;

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


}
