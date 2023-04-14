package com.bs.messervice.config;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.bs.messervice.Utils.typeHander.Json2ListStringJacksonTypeHandler;
import com.google.gson.reflect.TypeToken;
import org.apache.ibatis.type.JdbcType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MybatisPusConfig {
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
//            configuration.getTypeHandlerRegistry().register(Object.class, JdbcType.VARCHAR, new Json2ListStringJacksonTypeHandler(TypeToken.getParameterized(List.class, clazzz).getType();));
            configuration.getTypeHandlerRegistry().register(Object.class, JdbcType.VARCHAR, new FastjsonTypeHandler(Object.class));
        };
    }

}
