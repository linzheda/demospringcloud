package com.linzd.backsystem.core.article.entity;

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
 * 文章类型
 * </p>
 *
 * @author linzd
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ArticleType对象", description="文章类型")
public class ArticleType extends BaseEntity<ArticleType> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "父级id")
    private Long pid;

    @ApiModelProperty(value = "isn码")
    private String isn;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "等级")
    private Integer level;

    @ApiModelProperty(value = "排序")
    private Integer seq;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
