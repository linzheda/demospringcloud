package com.linzd.app.core.access.mapper;

import com.linzd.app.core.access.entity.SmsLog;
import com.linzd.app.core.access.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 描述  获取 验证码的发送情况
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/10/27 16:15
     **/
    @MapKey("id")
    Map<String, Object> getSmsCnt(String tel, Integer type);

    /**
     * 描述  获取最新的验证吗记录 校验用
     *
     * @author Lorenzo Lin
     * @params
     * @created 2020/11/13 11:27
     **/
    @MapKey("id")
    SmsLog getMaxNewSms(String tel, Integer type);
}
