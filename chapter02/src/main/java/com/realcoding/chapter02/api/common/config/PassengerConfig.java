package com.realcoding.chapter02.api.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "passenger")
@Data
public class PassengerConfig {

    private List<String> types;

}