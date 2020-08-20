package com.linzd.backsystem.core.user.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linzd.backsystem.core.user.entity.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 岗位 Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-08-18
 */
public interface JobMapper extends BaseMapper<Job> {

    /**
     * 描述  获取岗位的列表 分页
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/18 21:46
     **/
    IPage<Map> getJobList(Page<Map> page, Map<String, Object> condition);
}
