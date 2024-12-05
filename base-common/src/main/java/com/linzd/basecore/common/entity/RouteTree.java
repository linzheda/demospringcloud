package com.linzd.basecore.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 描述 路由树
 *
 * @author Lorenzo Lin
 * @created 2020年03月27日 14:09
 */
@Data
public class RouteTree<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long pid;
    private String text;
    private String name;
    private String path;
    private String component;
    private String redirect;
    private Map<String, Object> meta;
    private List<RouteTree> children;
}
