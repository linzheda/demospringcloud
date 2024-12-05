package com.linzd.app.core.access.entity;

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
 * 机构
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Organization对象", description="机构")
public class Organization extends BaseEntity<Organization> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "父级id")
    private Long pid;

    @ApiModelProperty(value = "机构代码")
    private String code;

    @ApiModelProperty(value = "树级路径")
    private String isn;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "等级")
    private Integer level;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "所属区域")
    private String areacode;

    @ApiModelProperty(value = "排序")
    private String seq;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
