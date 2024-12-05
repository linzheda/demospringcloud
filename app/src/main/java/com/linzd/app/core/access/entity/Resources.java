package com.linzd.app.core.access.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.linzd.basecore.common.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Resources对象", description="资源")
public class Resources extends BaseEntity<Resources> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父级菜单")
    private Long pid;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单类型 0系统1目录 2 菜单 3按钮")
    private Integer type;

    @ApiModelProperty(value = "路由")
    private String route;

    @ApiModelProperty(value = "参数")
    private String attr;

    @ApiModelProperty(value = "等级")
    private Integer level;

    @ApiModelProperty(value = "树级菜单的isn")
    private String isn;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "样式")
    private String css;

    @ApiModelProperty(value = "权限标识")
    private String premissions;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer seq;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
