package com.linzd.backsystem.core.docking.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.core.docking.entity.ThirdPartyDocking;
import com.linzd.backsystem.core.docking.mapper.ThirdPartyDockingMapper;
import com.linzd.backsystem.core.docking.service.ThirdPartyDockingService;
import com.linzd.basecore.common.entity.ResultPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 第三方平台表  服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-08-20
 */
@Service
public class ThirdPartyDockingServiceImpl extends ServiceImpl<ThirdPartyDockingMapper, ThirdPartyDocking> implements ThirdPartyDockingService {
    @Autowired
    private ThirdPartyDockingMapper mapper;

    /**
     * 描述  获取应用列表(分页)
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/20 18:53
     */
    @Override
    public ResultPojo getThirdPartyDockingList(Map<String, Object> condition) {
        long current= Long.valueOf(condition.get("current").toString());
        long size = Long.valueOf(condition.get("size").toString());
        Page<Map> page = new Page<>(current,size);
        IPage<Map> result=mapper.getThirdPartyDockingList(page,condition);
        return ResultPojo.success(result);
    }
}
