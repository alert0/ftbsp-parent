package com.dstz.agilebpm.sys.autoconfiguration.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用ab消息队列
 *
 * @author wacxhs
 */
@Import(AbMessageQueueAutoConfigurationSelector.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableAbMessageQueue {

    /**
     * 消息队列类型
     *
     * @return 消息队列类型
     */
    MessageQueueType value() default MessageQueueType.REDIS;

}
