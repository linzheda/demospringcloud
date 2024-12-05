package com.linzd.backsystem.core.dictionary.mapper;

import com.linzd.backsystem.core.dictionary.entity.Dictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典 Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-05-27
 */
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    /**
     * 描述  获取数据字典根据pid
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/15 16:41
     **/
    List<Map<String, Object>> getDictsByPid(Map<String, Object> condition);

    /**
     * 描述  删除字典和下级
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/16 16:29
     **/
    int delDict(Long id);
}
