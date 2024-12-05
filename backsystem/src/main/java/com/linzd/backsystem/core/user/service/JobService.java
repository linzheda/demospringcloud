package com.linzd.backsystem.core.user.service;

import com.linzd.backsystem.core.user.entity.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.basecore.common.entity.ResultPojo;

import java.util.Map;

/**
 * <p>
 * 岗位 服务类
 * </p>
 *
 * @author linzd
 * @since 2020-08-18
 */
public interface JobService extends IService<Job> {

    /**
     * 描述  获取岗位的分页列表数据
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/18 21:44
     **/
    ResultPojo getJobList(Map<String, Object> condition);
}
