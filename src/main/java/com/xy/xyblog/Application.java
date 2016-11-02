package com.xy.xyblog;

import com.xy.xyblog.config.JdbcConnectionSettings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by XiuYang on 2016/11/2.
 */
@SpringBootApplication
@EnableRedisHttpSession
@EnableConfigurationProperties({ JdbcConnectionSettings.class })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
