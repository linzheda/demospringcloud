package com.linzd.backsystem.user.service;

import com.linzd.backsystem.user.entity.RoleUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linzd.backsystem.utils.ResultUtil;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
public interface RoleUserService extends IService<RoleUser> {

    /**
     * 描述  删除无效的roleuser关联表的数据
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/8/12 10:49
     **/
    ResultUtil delRoleUserLink();
}
