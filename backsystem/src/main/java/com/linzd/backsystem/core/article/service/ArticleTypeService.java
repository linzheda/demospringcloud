package com.linzd.backsystem.core.article.service;

import com.linzd.basecore.common.entity.ResultPojo;
import com.linzd.backsystem.core.article.entity.ArticleType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 文章类型 服务类
 * </p>
 *
 * @author linzd
 * @since 2021-01-05
 */
public interface ArticleTypeService extends IService<ArticleType> {

    /**
     * 描述  根据pid 获取文章类型
     *
     * @author Lorenzo Lin
     * @params
     * @created 2021/1/5 10:30
     **/
    ResultPojo getListByPid(Map<String, Object> condition);

    /**
     * 描述  删除文章类型和下级
     *
     * @author Lorenzo Lin
     * @params
     * @created 2021/1/5 10:40
     **/
    ResultPojo delById(Long id);
}
