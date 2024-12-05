package com.linzd.backsystem.core.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.linzd.basecore.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章评论表
 * </p>
 *
 * @author linzd
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ArticleComment对象", description="文章评论表")
public class ArticleComment extends BaseEntity<ArticleComment> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userid;

    @ApiModelProperty(value = "文章id")
    private Long articleid;

    @ApiModelProperty(value = "评论内容")
    private String content;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
