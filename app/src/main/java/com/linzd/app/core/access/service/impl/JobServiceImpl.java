package com.linzd.app.core.access.service.impl;

import com.linzd.app.core.access.entity.Job;
import com.linzd.app.core.access.service.JobService;
import com.linzd.app.core.access.mapper.JobMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

}
