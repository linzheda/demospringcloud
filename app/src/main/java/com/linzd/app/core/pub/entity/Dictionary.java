package com.linzd.app.core.pub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.linzd.basecore.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Dictionary对象", description="字典")
public class Dictionary extends BaseEntity<Dictionary> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "字典键")
    private String dictkey;

    @ApiModelProperty(value = "字典值")
    private String value;

    @ApiModelProperty(value = "父级id")
    private Long pid;

    @ApiModelProperty(value = "等级")
    private Integer level;

    @ApiModelProperty(value = "排序")
    private Integer seq;

    @ApiModelProperty(value = "描述")
    private String description;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
