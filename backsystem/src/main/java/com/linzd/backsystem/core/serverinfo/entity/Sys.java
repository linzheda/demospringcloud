package com.linzd.backsystem.core.serverinfo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述 Sys系统实体类
 *
 * @author Lorenzo Lin
 * @created 2020年09月22日 10:24
 */
@Data
public class Sys implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 服务器名称
     */
    private String computerName;

    /**
     * 服务器Ip
     */
    private String computerIp;

    /**
     * 项目路径
     */
    private String userDir;

    /**
     * 操作系统
     */
    private String osName;

    /**
     * 系统架构
     */
    private String osArch;
}
