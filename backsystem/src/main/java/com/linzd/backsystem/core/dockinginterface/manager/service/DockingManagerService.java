package com.linzd.backsystem.core.dockinginterface.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.backsystem.core.dockinginterface.manager.entity.DockingInterface;
import com.linzd.basecore.common.entity.ResultPojo;

import java.util.Map;

/**
 * 描述 对外接口管理业务层
 *
 * @author Lorenzo Lin
 * @created 2020年09月16日 9:37
 */
public interface DockingManagerService extends IService<DockingInterface> {


    /**
     * 描述  获取对外接口
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/16 9:53
     **/
    ResultPojo getInterfaceUrl();

    /**
     * 描述  获取所有入库的接口分页列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/9/16 10:15
     **/
    ResultPojo getInterfaceList(Map<String, Object> condition);
}
