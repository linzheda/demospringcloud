package com.linzd.backsystem.core.dictionary.service;

import com.linzd.backsystem.core.dictionary.entity.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.basecore.common.entity.ResultPojo;

import java.util.Map;

/**
 * <p>
 * 字典 服务类
 * </p>
 *
 * @author linzd
 * @since 2020-05-27
 */
public interface DictionaryService extends IService<Dictionary> {
    /**
     * 描述  获取数据字典通过pid
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/15 16:38
     **/
    ResultPojo getDictsByPid(Map<String, Object> condition);

    /**
     * 描述  删除字典和下级
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/16 16:27
     **/
    ResultPojo delDict(Long id);
}
