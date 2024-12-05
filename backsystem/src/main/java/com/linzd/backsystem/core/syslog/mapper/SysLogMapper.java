package com.linzd.backsystem.core.syslog.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linzd.basecore.common.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 系统日志 Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-09-11
 */
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 描述  获取日志分页列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/11 16:44
     **/
    IPage<Map> getLogList(Page<Map> page, Map<String, Object> condition);

    /**
     * 描述  获取登录日志分页列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/14 11:54
     **/
    IPage<Map> getLoginLogList(Page<Map> page, Map<String, Object> condition);

    /**
     * 描述  获取日志时间列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/14 16:03
     **/
    IPage<Map> getLogTimeLine(Page<Map> page, Map<String, Object> condition);
}
