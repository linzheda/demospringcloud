/*
 * Copyright (c) 2005, 2018, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.linzd.app.core.access.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 描述 验证码日志实体类
 *
 * @author Lorenzo Lin
 * @created 2020年10月27日 10:50
 */
@Data
@Accessors(chain = true)
@ApiModel(value="SmsLog对象", description="验证码日志")
public class SmsLog extends Model<SmsLog> {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "手机号")
    private String tel;

    @ApiModelProperty(value = "验证码")
    private String sms;

    @ApiModelProperty(value = "发送验证码时间")
    private LocalDateTime smstime;


    @ApiModelProperty(value = "类型 关联字典表smstype")
    private Integer type;

    @ApiModelProperty(value = "校验结果 1成功0失败")
    private Integer result;

    @ApiModelProperty(value = "用户id")
    private Long userid;

}
