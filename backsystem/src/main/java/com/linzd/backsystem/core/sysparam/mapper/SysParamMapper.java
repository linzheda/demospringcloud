package com.linzd.backsystem.core.sysparam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linzd.backsystem.core.sysparam.entity.SysParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 系统参数 Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-08-07
 */
public interface SysParamMapper extends BaseMapper<SysParam> {

    /**
     * 描述  获取系统参数列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/18 20:11
     **/
    IPage<Map> getSysParamList(Page<Map> page, Map<String, Object> condition);
}
