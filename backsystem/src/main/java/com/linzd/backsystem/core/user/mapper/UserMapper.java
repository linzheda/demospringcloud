package com.linzd.backsystem.core.user.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linzd.backsystem.core.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 描述  获取用户列表
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/7/25 18:13
     **/
    IPage<Map> getUserList(Page<Map> page, Map<String, Object> condition);
}
