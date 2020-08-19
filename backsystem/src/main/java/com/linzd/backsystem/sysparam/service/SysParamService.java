package com.linzd.backsystem.sysparam.service;

import com.linzd.backsystem.sysparam.entity.SysParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.backsystem.utils.ResultUtil;

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
    ResultUtil getSysParamList(Map<String, Object> condition);
}
