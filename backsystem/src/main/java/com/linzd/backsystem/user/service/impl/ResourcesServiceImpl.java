package com.linzd.backsystem.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.backsystem.common.entity.Tree;
import com.linzd.backsystem.user.entity.Resources;
import com.linzd.backsystem.user.mapper.ResourcesMapper;
import com.linzd.backsystem.user.service.ResourcesService;
import com.linzd.backsystem.utils.ResultUtil;
import com.linzd.backsystem.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类  资源菜单
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements ResourcesService {

    @Autowired
    private ResourcesMapper resourcesMapper;

    /**
     * 描述  获取资源菜单通用过用户id
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/3/23 11:24
     **/
    @Override
    public ResultUtil getResourcesByUserId(Integer userId) {
        List<Tree> menu=resourcesMapper.getResourcesByUserId(userId);
        TreeUtils tu=new TreeUtils();
        List<Tree> tree= tu.toTree(menu);

        return ResultUtil.success(tree);
    }
}
