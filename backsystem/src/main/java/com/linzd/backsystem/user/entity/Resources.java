package com.linzd.backsystem.user.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author linzd
 * @since 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Alias("Resources")
@ApiModel(value="Resources对象", description="")
public class Resources extends Model<Resources> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "父级菜单")
    private Integer pid;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单类型 1目录 2 菜单 3按钮")
    private Integer type;

    @ApiModelProperty(value = "资源路径")
    private String path;

    @ApiModelProperty(value = "路由")
    private String route;

    @ApiModelProperty(value = "参数")
    private String attr;

    @ApiModelProperty(value = "等级")
    private Integer rank;

    @ApiModelProperty(value = "树级菜单的isn")
    private String isn;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "样式")
    private String css;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer seq;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createtime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatetime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
