package com.imp;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.imp.util.id.InsertInterceptor;
import com.imp.util.admin.AppEnvPreparedListener;
import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import tk.mybatis.spring.annotation.MapperScan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@MapperScan("com.imp.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.addListeners(new AppEnvPreparedListener());
        final ApplicationContext ctx = application.run(args);
        Environment environment = ctx.getEnvironment();
        System.out.println("项目已启动 http://localhost:" + environment.getProperty("local.server.port"));

        DocsConfig config = new DocsConfig();
        config.setProjectPath(System.getProperty("user.dir") + "\\src\\main\\java\\com"); // 项目根目录
        config.setProjectName("museum"); // 项目名称
        config.setApiVersion("museum_V1.0");       // 声明该API的版本
        config.setDocsPath("D:\\api"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档

    }


    @Bean
    public Converter<String, Date> dateConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                if (StringUtils.isEmpty(source)) {
                    return null;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                try {
                    date = sdf.parse(source);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }

    @Bean
    public Converter<String, String> stringConvert() {
        return new Converter<String, String>() {
            @Override
            public String convert(String source) {
                if (StringUtils.isEmpty(source)) {
                    return null;
                }
                return source;
            }
        };
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.DisableCircularReferenceDetect);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        List<MediaType> mediaTypes = new ArrayList<>(1);
        mediaTypes.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(mediaTypes);
        return new HttpMessageConverters(fastConverter);
    }

    @Bean
    public InsertInterceptor insertInterceptor() {
        return new InsertInterceptor();
    }

}
