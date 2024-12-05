package com.linzd.backsystem.core.sysparam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.core.sysparam.entity.SysParam;
import com.linzd.backsystem.core.sysparam.mapper.SysParamMapper;
import com.linzd.backsystem.core.sysparam.service.SysParamService;
import com.linzd.basecore.common.entity.ResultPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 系统参数 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-08-07
 */
@Service
public class SysParamServiceImpl extends ServiceImpl<SysParamMapper, SysParam> implements SysParamService {

    @Autowired
    private SysParamMapper mapper;


    /**
     * 描述  获取系统参数列表 (分页)
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/18 20:08
     */
    @Override
    public ResultPojo getSysParamList(Map<String, Object> condition) {
        long current= Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current,size);
        IPage<Map> result=mapper.getSysParamList(page,condition);
        return ResultPojo.success(result);
    }
}
