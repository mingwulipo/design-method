package org.study.design.strategy.impl;

import org.study.design.strategy.ConsumeStrategy;
import org.study.design.strategy.common.MqTypeEnum;
import org.study.design.strategy.common.StrategyType;

/**
 * @author lipo
 * @version v1.0
 * @desc
 * @date 2019-10-15 17:18
 */
@StrategyType(MqTypeEnum.ORDER)
public class OrderConsumeStrategyImpl implements ConsumeStrategy {
    /**
     * @desc 消费标签消息
     * @author lipo
     * @date 2019-10-15 17:19
     * @param
     * @return
     **/
    @Override
    public String consume(String msg) {
        System.out.println(msg + ", 订单添加成功");
        return "ok";
    }
}
