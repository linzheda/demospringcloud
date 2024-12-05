package com.linzd.basecore.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.linzd.basecore.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 描述 mybatis-plus 自动填充
 *
 * @author Lorenzo Lin
 * @created 2020年08月13日 11:07
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Autowired
    private HttpServletRequest request;

    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId= JwtTokenUtil.getUserIdByToken(request.getHeader("Authorization"));;
        // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createtime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createby", Long.class, userId);
        this.strictInsertFill(metaObject, "updatetime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateby", Long.class, userId);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId=JwtTokenUtil.getUserIdByToken(request.getHeader("Authorization"));;
        // 起始版本 3.3.0(推荐使用)
        this.strictUpdateFill(metaObject, "updatetime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updateby", Long.class, userId);
    }
}
