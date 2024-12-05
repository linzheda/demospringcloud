package com.linzd.backsystem.core.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.backsystem.core.article.entity.ArticleType;
import com.linzd.backsystem.core.article.mapper.ArticleTypeMapper;
import com.linzd.backsystem.core.article.service.ArticleTypeService;
import com.linzd.basecore.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章类型 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2021-01-05
 */
@Service
public class ArticleTypeServiceImpl extends ServiceImpl<ArticleTypeMapper, ArticleType> implements ArticleTypeService {

    @Autowired
    private ArticleTypeMapper mapper;
    /**
     * 描述  根据pid 获取文章类型
     *
     * @param condition
     * @author Lorenzo Lin
     * @params
     * @created 2021/1/5 10:30
     */
    @Override
    public ResultPojo getListByPid(Map<String, Object> condition) {
        List<Map<String,Object>> resources = mapper.getListByPid(condition);
        TreeUtil tu=new TreeUtil();
        List<Map<String,Object>> tree=tu.toMapTree(resources,null);
        return ResultPojo.success(tree);
    }

    /**
     * 描述  删除文章类型和下级
     *
     * @param id
     * @author Lorenzo Lin
     * @params
     * @created 2021/1/5 10:40
     */
    @Override
    public ResultPojo delById(Long id) {
        int result= mapper.delById(id);
        String msg = result >0 ? "删除成功" : "删除失败";
        return ResultPojo.success(msg, result);
    }
}
