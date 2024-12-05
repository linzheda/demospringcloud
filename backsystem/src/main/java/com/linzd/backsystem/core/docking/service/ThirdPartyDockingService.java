package com.linzd.backsystem.core.docking.service;

import com.linzd.backsystem.core.docking.entity.ThirdPartyDocking;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.basecore.common.entity.ResultPojo;

import java.util.Map;

/**
 * <p>
 * 第三方平台表  服务类
 * </p>
 *
 * @author linzd
 * @since 2020-08-20
 */
public interface ThirdPartyDockingService extends IService<ThirdPartyDocking> {
    
    /**
     * 描述  获取应用列表(分页)
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/20 18:53
     **/
    ResultPojo getThirdPartyDockingList(Map<String, Object> condition);
}
