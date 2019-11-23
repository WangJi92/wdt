package com.wudang.sst.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 相关配置文件
 * @author jet
 * @date 23-11-2019
 */
@Configuration
@ConfigurationProperties(prefix = "wudang")
@Data
public class WudangProperties {

    private String name;


}
