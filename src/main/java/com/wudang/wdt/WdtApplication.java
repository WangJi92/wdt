package com.wudang.wdt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WdtApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(WdtApplication.class);
        //https://blog.csdn.net/qq_38361800/article/details/90437824 复制剪切板失败
        builder.headless(false).run(args);
    }

}
