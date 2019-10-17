package org.study.design.strategy.common;

/**
 * @author lipo
 * @version v1.0
 * @desc
 * @date 2019-10-15 17:24
 */
public enum  MqTypeEnum {
    LABEL((byte)1, "标签"),
    ORDER((byte)2, "订单"),
    SMS((byte)3, "短信"),
    WECHAT((byte)4, "微信")
    ;

    private byte code;
    private String msg;

    MqTypeEnum(byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据code查询枚举
     * @param code
     * @return
     */
    public static MqTypeEnum getEnumByCode(Byte code) {
        if (code == null) {
            return null;
        }

        for (MqTypeEnum mqTypeEnum :
                values()) {
            if (mqTypeEnum.code == code) {
                return mqTypeEnum;
            }
        }

        return null;
    }

    public byte getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
