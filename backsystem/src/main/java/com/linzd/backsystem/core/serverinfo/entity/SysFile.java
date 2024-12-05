package com.linzd.backsystem.core.serverinfo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述 SysFile系统文件实体类
 *
 * @author Lorenzo Lin
 * @created 2020年09月22日 10:25
 */
@Data
public class SysFile implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 盘符路径
     */
    private String dirName;

    /**
     * 盘符类型
     */
    private String sysTypeName;

    /**
     * 文件类型
     */
    private String typeName;

    /**
     * 总大小
     */
    private String total;

    /**
     * 剩余大小
     */
    private String free;

    /**
     * 已经使用量
     */
    private String used;

    /**
     * 资源的使用率
     */
    private double usage;
}
