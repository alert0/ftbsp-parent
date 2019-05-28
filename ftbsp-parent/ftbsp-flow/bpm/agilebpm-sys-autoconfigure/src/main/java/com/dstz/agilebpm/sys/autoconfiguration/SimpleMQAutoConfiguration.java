package com.dstz.agilebpm.sys.autoconfiguration;

import cn.hutool.extra.mail.MailAccount;
import com.dstz.sys.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 缓存相关配置
 *
 * @author jeff
 * @date 2018-8-12 22:36:19
 */
@Configuration
@EnableConfigurationProperties(MQMailConfigProperties.class)
public class SimpleMQAutoConfiguration {

    @Autowired
    private MQMailConfigProperties mQMailConfigProperties;

    @Component
    class MailAccountAutoConfiguration implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            if (event.getApplicationContext().getParent() == null) {
                setEmailConfiguration();
            }
        }
    }

    private void setEmailConfiguration() {
        MailAccount account = new MailAccount();

        account.setHost(mQMailConfigProperties.getSendHost());
        account.setPort(mQMailConfigProperties.getSendPort());
        account.setFrom(mQMailConfigProperties.getMailAddress());
        account.setUser(mQMailConfigProperties.getNickName());
        account.setPass(mQMailConfigProperties.getPassword());
        account.setSslEnable(mQMailConfigProperties.isSSL());

        EmailUtil.setAccount(account);
    }

}
