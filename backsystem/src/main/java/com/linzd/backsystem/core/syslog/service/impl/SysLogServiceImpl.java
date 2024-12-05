package com.linzd.backsystem.core.syslog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.basecore.common.entity.SysLog;
import com.linzd.backsystem.core.syslog.mapper.SysLogMapper;
import com.linzd.backsystem.core.syslog.service.SysLogService;
import com.linzd.basecore.common.entity.ResultPojo;
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
    public ResultPojo getLogList(Map<String, Object> condition) {
        long current= Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current,size);
        IPage<Map> result;
        if("login".equals(condition.get("type"))){
            result=mapper.getLoginLogList(page,condition);
            //登录日志处理下 登录状态
            for(Map<String,Object> record:result.getRecords() ){
                if(((String)record.get("outresult")).contains("\"code\":200")){
                    record.put("loginstatus", "登录成功");
                }else{
                    record.put("loginstatus", "登录失败");
                }
            }
        }else{
            result=mapper.getLogList(page,condition);
        }
        return ResultPojo.success(result);
    }

    /**
     * 描述  获取日志时间线
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/14 16:00
     */
    @Override
    public ResultPojo getLogTimeLine(Map<String, Object> condition) {
        long current= Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current,size);
        IPage<Map> result= mapper.getLogTimeLine(page,condition);;
        return ResultPojo.success(result);
    }
}
