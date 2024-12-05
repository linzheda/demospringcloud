package com.linzd.basecore.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author linzd
 * @since 2020-09-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SysLog对象", description="系统日志")
public class SysLog extends BaseEntity<SysLog> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "系统模块")
    private String module;

    @ApiModelProperty(value = "操作说明")
    private String operdesc;


    @ApiModelProperty(value = "操作类型")
    private Integer opertype;

    @ApiModelProperty(value = "操作人id")
    private Long operatorid;

    @ApiModelProperty(value = "IP地址")
    private String ipaddr;

    @ApiModelProperty(value = "请求地址")
    private String requrl;

    @ApiModelProperty(value = "请求参数")
    private String reqparam;

    @ApiModelProperty(value = "请求方式")
    private String reqtype;

    @ApiModelProperty(value = "操作时间")
    private LocalDateTime opertime;

    @ApiModelProperty(value = "操作方法")
    private String reqmethod;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "返回结果")
    private String outresult;

    @ApiModelProperty(value = "如果是对外接口 则记录client_id")
    private String clientId;

    @ApiModelProperty(value = "异常信息")
    private String errormsg;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
