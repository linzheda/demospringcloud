package com.linzd.backsystem.core.syslog.service;

import com.linzd.backsystem.common.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.backsystem.utils.ResultUtil;

import java.util.Map;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author linzd
 * @since 2020-09-11
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 描述  获取操作日志列表 分页
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/11 16:42
     **/
    ResultUtil getLogList(Map<String, Object> condition);
}
