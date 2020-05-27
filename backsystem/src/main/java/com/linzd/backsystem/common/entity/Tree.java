package com.linzd.backsystem.common.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * 描述 树级菜单实体类
 *
 * @author Lorenzo Lin
 * @created 2020年03月23日 15:23
 */
@Data
@Alias("Tree")
public class Tree<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer pid;

    private String text;

    private T attr;

    private List<Tree> children;
}
