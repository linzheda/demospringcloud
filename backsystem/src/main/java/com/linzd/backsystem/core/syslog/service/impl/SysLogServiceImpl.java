package com.linzd.backsystem.core.syslog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.common.entity.SysLog;
import com.linzd.backsystem.core.syslog.mapper.SysLogMapper;
import com.linzd.backsystem.core.syslog.service.SysLogService;
import com.linzd.backsystem.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-09-11
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Autowired
    private SysLogMapper mapper;

    /**
     * 描述  获取操作日志列表 分页
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/11 16:42
     */
    @Override
    public ResultUtil getOperLogList(Map<String, Object> condition) {
        long current= Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current,size);
        IPage<Map> result=mapper.getOperLogList(page,condition);
        return ResultUtil.success(result);
    }
}
