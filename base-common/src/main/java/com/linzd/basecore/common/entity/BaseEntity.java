package com.linzd.basecore.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 描述 mybatis-plus 基础实体类 用来统一管理 创建时间 创建人 最后更新时间 最后更新人
 *
 * @author Lorenzo Lin
 * @created 2020年08月13日 10:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "基础实体类", description = "")
public class BaseEntity<T extends BaseEntity<?>> extends Model<T> {

    @ApiModelProperty(value = "创建人")
    @TableField(value = "createby", fill = FieldFill.INSERT) // 新增执行
    private Long createby;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "createtime", fill = FieldFill.INSERT)
    private LocalDateTime createtime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "updateby", fill = FieldFill.INSERT_UPDATE) // 新增和更新执行
    private Long updateby;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "updatetime", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatetime;


}
