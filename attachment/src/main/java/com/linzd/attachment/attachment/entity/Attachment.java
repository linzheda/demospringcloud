package com.linzd.attachment.attachment.entity;

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
 * 
 * </p>
 *
 * @author linzd
 * @since 2020-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Attachment对象", description="")
public class Attachment extends Model<Attachment> implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "附件名称")
    private String filename;

    @ApiModelProperty(value = "附件别名")
    private String filealias;

    @ApiModelProperty(value = "附件类型")
    private String type;

    @ApiModelProperty(value = "附件的url地址")
    private String fileurl;

    @ApiModelProperty(value = "附件路径")
    private String filepath;

    @ApiModelProperty(value = "附件大小")
    private Double filesize;

    @ApiModelProperty(value = "附件状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createtime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatetime;


}
