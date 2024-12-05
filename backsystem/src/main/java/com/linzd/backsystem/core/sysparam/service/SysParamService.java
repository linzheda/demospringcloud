package com.linzd.backsystem.core.sysparam.service;

import com.linzd.backsystem.core.sysparam.entity.SysParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.basecore.common.entity.ResultPojo;

import java.util.Map;

/**
 * <p>
 * 系统参数 服务类
 * </p>
 *
 * @author linzd
 * @since 2020-08-07
 */
public interface SysParamService extends IService<SysParam> {

    /**
     * 描述  获取系统参数列表 (分页)
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/18 20:08
     **/
    ResultPojo getSysParamList(Map<String, Object> condition);
}
