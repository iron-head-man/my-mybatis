package com.xiaoxing.mybatis06.type;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * JDBC类型枚举类
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/08 22:45
 */
public enum JdbcType {

    INTEGER(Types.INTEGER),
    FLOAT(Types.FLOAT),
    DOUBLE(Types.DOUBLE),
    DECIMAL(Types.DECIMAL),
    VARCHAR(Types.VARCHAR),
    TIMESTAMP(Types.TIMESTAMP);

    public final int TYPE_CODE;

    private static Map<Integer, JdbcType> codeLookup = new HashMap<>();

    // 将数字对应的枚举类型放入map
    static {
        for (JdbcType value : JdbcType.values()) {
            codeLookup.put(value.TYPE_CODE, value);
        }
    }

    JdbcType(int code) {
        this.TYPE_CODE = code;
    }

    public static JdbcType forCode(int code) {
        return codeLookup.get(code);
    }

}
