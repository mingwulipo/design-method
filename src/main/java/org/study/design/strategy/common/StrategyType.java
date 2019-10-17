package org.study.design.strategy.common;

import java.lang.annotation.*;

/**
 * @author lipo
 * @version v1.0
 * @desc
 * @date 2019-10-15 17:22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrategyType {
    /**
     * @desc mq消息类型
     * @author lipo
     * @date 2019-10-15 17:26
     * @param
     * @return
     **/
    MqTypeEnum value();
}
