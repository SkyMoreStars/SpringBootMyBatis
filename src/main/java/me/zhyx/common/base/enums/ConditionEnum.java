package me.zhyx.common.base.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: zhyx
 * Date:2018/5/7
 * Time:21:31
 */
public enum ConditionEnum {
    EQ("="),
    NEQ("!="),
    IN("in(%s)"),
    BETWEEEN("BETWEEN %s AND %s"),
    LT("&lt"),
    LTEQ("&lt;="),
    GT("&gt"),
    GTEQ("&gt="),
    AND("AND"),
    OR("OR"),
    NOT("!"),
    LIKE("LIKE '%s'");
    private String sign;

    ConditionEnum(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public static Map<String, Object> operator(ConditionEnum conditionEnum, String val) {
        Map<String, Object> map = new HashMap<>();
        String res = null;
        boolean isHasSetVal = false;
        if (conditionEnum.name().equals(ConditionEnum.BETWEEEN.name())) {
            String[] vals = val.split(",");
            if (vals.length != 2) {
                throw new RuntimeException("Between params is A and B,Please verify that your parameter format is A,B");
            }
            res = String.format(conditionEnum.getSign(), vals[0], vals[1]);
            isHasSetVal = true;
        } else if (conditionEnum.name().equals(ConditionEnum.LIKE.name())) {
            res = String.format(conditionEnum.getSign(), "%" + val + "%");
            isHasSetVal = true;
        } else if (conditionEnum.name().equals(ConditionEnum.IN.name())) {
            res = String.format(conditionEnum.getSign(), val);
            isHasSetVal = true;
        }else {
            res = String.format(conditionEnum.getSign(), val);
        }
        map.put("hasSetVal", isHasSetVal);
        map.put("condition", res);
        return map;
    }
}
