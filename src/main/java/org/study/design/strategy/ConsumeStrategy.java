package org.study.design.strategy;

/**
 * @author lipo
 * @version v1.0
 * @desc
 * @date 2019-10-15 17:16
 */
public interface ConsumeStrategy {
    /**
     * 消费消息
     * @author lipo
     * @date 2019-10-15 17:17
     * @param
     * @return
     **/
    String consume(String msg);
}
