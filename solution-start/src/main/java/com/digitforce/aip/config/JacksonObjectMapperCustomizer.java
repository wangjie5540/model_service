package com.digitforce.aip.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonObjectMapperCustomizer implements BeanPostProcessor {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String dateTimeFormat;

    @Override
    public Object postProcessBeforeInitialization(@NotNull Object bean, @NotNull String beanName)
            throws BeansException {
        if (bean instanceof ObjectMapper) {
            ObjectMapper objectMapper = (ObjectMapper) bean;
            SimpleModule module = new SimpleModule();
            //自定义日期、时间类型格式化
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
            module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
            module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(timeFormatter));
            module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));
            module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(timeFormatter));

            objectMapper.registerModule(new JavaTimeModule()); //默认的Java 8 Time库支持
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.registerModule(module);
            objectMapper.enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS.mappedFeature());
            // TODO 后续考虑去掉注释，使用驼峰
//            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        }
        return bean;
    }
}
