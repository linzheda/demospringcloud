package com.linzd.backsystem.common.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述 分页实体
 *
 * @author Lorenzo Lin
 * @created 2020年08月11日 17:28
 */
@Data
public class PageCondition<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private long current;
    private long size;
    private T condition;


    public Page getPage(){
        return new Page(current, size);
    }


}
