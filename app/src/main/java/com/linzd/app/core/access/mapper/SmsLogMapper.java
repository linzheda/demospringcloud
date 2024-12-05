package com.linzd.app.core.access.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linzd.app.core.access.entity.SmsLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 短信验证码 Mapper 接口
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@Mapper
public interface SmsLogMapper extends BaseMapper<SmsLog> {

}
