package com.linzd.backsystem.dictionary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.dictionary.entity.Dictionary;
import com.linzd.backsystem.dictionary.mapper.DictionaryMapper;
import com.linzd.backsystem.dictionary.service.DictionaryService;
import com.linzd.backsystem.utils.ResultUtil;
import com.linzd.backsystem.utils.TreeUtils;
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
    public ResultUtil getDictsByPid(Map<String, Object> condition) {
        List<Map<String,Object>> resources = mapper.getDictsByPid(condition);
        TreeUtils tu=new TreeUtils();
        List<Map<String,Object>> tree=tu.toMapTree(resources,null);
        return ResultUtil.success(tree);
    }

    /**
     * 描述  删除字典和下级
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/16 16:48
     **/
    @Override
    public ResultUtil delDict(Long id) {
        int result= mapper.delDict(id);
        String msg = result >0 ? "删除成功" : "删除失败";
        return ResultUtil.success(msg, result);
    }
}
