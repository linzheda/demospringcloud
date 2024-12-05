package com.linzd.backsystem.core.docking.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linzd.backsystem.core.docking.entity.ThirdPartyDocking;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 第三方平台表  Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-08-20
 */
public interface ThirdPartyDockingMapper extends BaseMapper<ThirdPartyDocking> {

    /**
     * 描述  获取应用列表(分页)
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/20 18:54
     **/
    IPage<Map> getThirdPartyDockingList(Page<Map> page, Map<String, Object> condition);
}
