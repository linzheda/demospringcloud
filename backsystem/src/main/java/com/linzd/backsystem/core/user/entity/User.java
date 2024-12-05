package com.linzd.backsystem.core.user.entity;

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
 *
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "User对象", description = "")
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "登录名")
    private String loginname;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "组织机构id (和组织机构相关联)")
    private Long orgid;

    @ApiModelProperty(value = "岗位id")
    private Long jobid;

    @ApiModelProperty(value = "身份证号码")
    private String idcard;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "手机号")
    private String tel;

    @ApiModelProperty(value = "1男2 女")
    private Integer sex;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "标签,分隔")
    private String tag;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "排序")
    private Integer seq;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "令牌")
    private String token;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
