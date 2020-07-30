package com.linzd.backsystem.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 机构
 * </p>
 *
 * @author linzd
 * @since 2020-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Organization对象", description="机构")
public class Organization extends Model<Organization> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "父级id")
    private Long parentid;

    @ApiModelProperty(value = "机构代码")
    private String code;

    @ApiModelProperty(value = "树级路径")
    private String isn;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "等级")
    private String rank;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "所属区域")
    private String areacode;

    @ApiModelProperty(value = "排序")
    private String seq;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createtime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatetime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
