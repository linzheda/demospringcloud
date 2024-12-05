package com.linzd.backsystem.core.user.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;


/**
 * 描述   菜单类型 0系统1目录 2 菜单 3按钮
 *
 * @author Lorenzo Lin
 * @created 2020年05月27日 11:27
 */
@Getter
public enum ResourcesTypeEnum  implements IEnum<Integer> {
    ZERO(0, "系统"),
    ONE(1, "目录"),
    TWO(2, "菜单"),
    THREE(3, "按钮");

    private final int value;
    private final String desc;

    ResourcesTypeEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }


}