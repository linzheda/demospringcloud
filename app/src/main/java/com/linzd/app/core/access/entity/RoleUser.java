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
 * 角色用户关联
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="RoleUser对象", description="角色用户关联")
public class RoleUser extends BaseEntity<RoleUser> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色id")
    private Long roleid;

    @ApiModelProperty(value = "用户id")
    private Long userid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
