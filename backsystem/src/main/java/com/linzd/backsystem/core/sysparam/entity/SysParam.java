package com.linzd.backsystem.core.sysparam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.linzd.basecore.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统参数
 * </p>
 *
 * @author linzd
 * @since 2020-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysParam对象", description = "系统参数")
public class SysParam extends BaseEntity<SysParam> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "参数名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "参数值")
    private String value;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "状态 禁用启用")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer seq;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
