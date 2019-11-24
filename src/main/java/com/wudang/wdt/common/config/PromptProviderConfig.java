package com.wudang.wdt.common.config;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

/**
 * shell 命令展示配置
 * @author jet
 * @date 20-11-2019
 */
@Configuration
public class PromptProviderConfig {

    @Bean
    public PromptProvider myPromptProvider() {
        return () -> new AttributedString("wdt$ ", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}
