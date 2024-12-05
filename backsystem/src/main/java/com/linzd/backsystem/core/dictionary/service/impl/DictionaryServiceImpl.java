package com.linzd.backsystem.core.dictionary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.core.dictionary.entity.Dictionary;
import com.linzd.backsystem.core.dictionary.mapper.DictionaryMapper;
import com.linzd.backsystem.core.dictionary.service.DictionaryService;
import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.basecore.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-05-27
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    @Autowired
    private DictionaryMapper mapper;

    /**
     * 描述  获取字典根据pid
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/16 16:48
     **/
    @Override
    public ResultPojo getDictsByPid(Map<String, Object> condition) {
        List<Map<String,Object>> resources = mapper.getDictsByPid(condition);
        TreeUtil tu=new TreeUtil();
        List<Map<String,Object>> tree=tu.toMapTree(resources,null);
        return ResultPojo.success(tree);
    }

    /**
     * 描述  删除字典和下级
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/16 16:48
     **/
    @Override
    public ResultPojo delDict(Long id) {
        int result= mapper.delDict(id);
        String msg = result >0 ? "删除成功" : "删除失败";
        return ResultPojo.success(msg, result);
    }
}
