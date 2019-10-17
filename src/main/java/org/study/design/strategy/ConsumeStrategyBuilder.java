package org.study.design.strategy;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.study.design.strategy.common.MqTypeEnum;
import org.study.design.strategy.common.StrategyType;
import org.study.design.util.PackageUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lipo
 * @version v1.0
 * @desc
 * @date 2019-10-15 17:20
 */
@Component
public class ConsumeStrategyBuilder {

    /**
     * 转发map
     */
    private static Map<MqTypeEnum, ConsumeStrategy> map = new ConcurrentHashMap<>();

    //初始化转发map
    static {
        List<String> list = PackageUtil.resolveAllClass(ConsumeStrategyBuilder.class.getPackage().getName() + ".impl");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        if (!CollectionUtils.isEmpty(list)) {
            for (String classFullName :
                    list) {
                try {
                    Class<?> aClass = classLoader.loadClass(classFullName);
                    StrategyType annotation = aClass.getAnnotation(StrategyType.class);

                    Object o = aClass.newInstance();
                    if (o instanceof ConsumeStrategy && annotation != null) {
                        map.put(annotation.value(), (ConsumeStrategy)o);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }

    /**
     * 转发消费消息
     * @param mqTypeEnum
     * @param msg
     * @return
     */
    public String consume(MqTypeEnum mqTypeEnum, String msg) {
        ConsumeStrategy consumeStrategy = map.get(mqTypeEnum);
        return consumeStrategy.consume(msg);
    }

}
