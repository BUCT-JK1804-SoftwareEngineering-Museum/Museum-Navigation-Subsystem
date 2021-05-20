package com.imp.util.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.util.StringUtils;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.util.Properties;

/**
 * springBoot 配置加载完成时
 */
@Configuration
@Slf4j
public class AppEnvPreparedListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    /**
     * 加载properties时
     * 如果没有fileRootPath属性则定义在运行目录/webFiles/下
     */
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        String fileRootPath = environment.getProperty("fileRootPath");
        if (StringUtils.isEmpty(fileRootPath)) {
            fileRootPath = new File("").getAbsolutePath() + "/data/upload/";
            Properties props = new Properties();
            props.put("fileRootPath", fileRootPath);
            environment.getPropertySources().addFirst(new PropertiesPropertySource("myProps", props));
        }
        PathUtil.setFileRootPath(fileRootPath);
        log.info("项目上传文件根目录:[{}]", fileRootPath);

    }

    /**
     * 文件上传临时路径
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = System.getProperty("user.dir") + "/data/tmp";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }
}
