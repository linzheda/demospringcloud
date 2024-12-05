package com.linzd.backsystem.core.dockinginterface.manager.entity;

import com.linzd.basecore.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 对外接口表
 * </p>
 *
 * @author linzd
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="DockingInterface对象", description="对外接口表")
public class DockingInterface extends BaseEntity<DockingInterface> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "接口名称")
    private String name;

    @ApiModelProperty(value = "接口地址")
    private String url;

    @ApiModelProperty(value = "方法名称")
    private String method;

    @ApiModelProperty(value = "请求方式")
    private String type;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "访问路径")
    private String path;

    @ApiModelProperty(value = "备注")
    private String remark;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
