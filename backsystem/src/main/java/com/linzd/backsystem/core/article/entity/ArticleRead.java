package com.linzd.backsystem.core.article.entity;

import com.linzd.basecore.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文章互动表(已读,点赞)
 * </p>
 *
 * @author linzd
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ArticleRead对象", description="文章互动表(已读,点赞)")
public class ArticleRead extends BaseEntity<ArticleRead> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "文章id")
    private Long articleid;

    @ApiModelProperty(value = "用户id")
    private Long userid;

    @ApiModelProperty(value = "是否阅读 1已读 ")
    private Integer isread;

    @ApiModelProperty(value = "是否点赞 1点赞")
    private Integer isstart;

    @ApiModelProperty(value = "点赞时间")
    private LocalDateTime starttime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
