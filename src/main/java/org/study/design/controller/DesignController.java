package org.study.design.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.design.strategy.ConsumeStrategyBuilder;
import org.study.design.strategy.common.MqTypeEnum;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-10-17 10:59
 */
@RestController
@RequestMapping("design")
public class DesignController {

    @Autowired
    private ConsumeStrategyBuilder consumeStrategyBuilder;

    /**
     * http://localhost:8080/design/strategy?code=1&msg=hehe
     * 策略模式
     * @param code
     * @param msg
     * @return
     */
    @GetMapping("strategy")
    public String strategy(Byte code, String msg) {
        MqTypeEnum mqTypeEnum = MqTypeEnum.getEnumByCode(code);
        return consumeStrategyBuilder.consume(mqTypeEnum, msg);
    }

}
