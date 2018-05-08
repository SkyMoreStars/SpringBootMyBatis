package me.zhyx.common.base.entity;

import me.zhyx.common.base.enums.ConditionEnum;

/**
 * Author: zhyx
 * Date:2018/5/7
 * Time:21:29
 */
public class Condition {
    private String column;
    private ConditionEnum conditionEnum;
    private String value;

    public Condition(String column, ConditionEnum conditionEnum, String value) {
        this.column = column;
        this.conditionEnum = conditionEnum;
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public ConditionEnum getConditionEnum() {
        return conditionEnum;
    }

    public void setConditionEnum(ConditionEnum conditionEnum) {
        this.conditionEnum = conditionEnum;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
