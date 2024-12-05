package com.linzd.basecore.common.enums;

/**
 * 描述 操作业务类型
 *
 * @author Lorenzo Lin
 * @created 2020年09月11日 9:19
 */
public enum OperType {
    /**
     * 其它
     */
    OTHER,
    /**
     * 查询
     */
    SELECT,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 登录
     */
    LOGIN,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 生成代码
     */
    GENCODE,

    /**
     * 清空
     */
    CLEAN,
}
