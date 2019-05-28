package com.dstz.agilebpm.sys.autoconfiguration.annotation;

import com.dstz.agilebpm.sys.autoconfiguration.AbMessageQueueAutoConfiguration;
import com.dstz.agilebpm.sys.autoconfiguration.AbRedisMessageQueueAutoConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * ab消息队列自动装配选择器
 *
 * @author wacxhs
 */
public class AbMessageQueueAutoConfigurationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        Map<String, Object> attr = annotationMetadata.getAnnotationAttributes(EnableAbMessageQueue.class.getName());
        MessageQueueType messageQueueType = (MessageQueueType)attr.get("value");
        if (MessageQueueType.REDIS.equals(messageQueueType)) {
            return new String[]{AbRedisMessageQueueAutoConfiguration.class.getName()};
        } else if (MessageQueueType.JMS.equals(messageQueueType)) {
            return new String[]{AbMessageQueueAutoConfiguration.class.getName()};
        }
        return new String[0];
    }
}
