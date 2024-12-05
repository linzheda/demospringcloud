package com.linzd.backsystem.core.docking.entity;

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
 * 第三方平台表 
 * </p>
 *
 * @author linzd
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ThirdPartyDocking对象", description="第三方平台表 ")
public class ThirdPartyDocking extends BaseEntity<ThirdPartyDocking> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "代码")
    private String code;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "应用标识唯一")
    private String clientId;

    @ApiModelProperty(value = "秘钥")
    private String clientSecret;

    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "参数")
    private String attr;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
