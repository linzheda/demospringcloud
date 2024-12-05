package com.linzd.backsystem.core.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.core.user.entity.Job;
import com.linzd.backsystem.core.user.mapper.JobMapper;
import com.linzd.backsystem.core.user.service.JobService;
import com.linzd.basecore.common.entity.ResultPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-08-18
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Autowired
    private JobMapper mapper;

    /**
     * 描述  获取岗位的分页列表数据
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/18 21:44
     */
    @Override
    public ResultPojo getJobList(Map<String, Object> condition) {
        long current= Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current,size);
        IPage<Map> result=mapper.getJobList(page,condition);
        return ResultPojo.success(result);
    }
}
