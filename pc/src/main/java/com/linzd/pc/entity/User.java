package com.linzd.pc.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体类
 * @author 林哲达
 *Created by linzd on 2016/11/20.
 */
@Data
@ApiModel(value="User",description="用户对象user")
public class User implements Serializable {
  
	private static final long serialVersionUID = -7022019403547512027L;

    /**
     * 用户id
     */
	private Integer id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 令牌
     */
    private String token;
    /**
     * 手机
     */
    private String tel;

    /**
     * 性别
     */
    private String sex;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createtime;

    /**
     * 最后修改时间
     */
    private String updatetime;


}
