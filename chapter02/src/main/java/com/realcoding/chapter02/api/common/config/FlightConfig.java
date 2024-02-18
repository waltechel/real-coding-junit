package com.realcoding.chapter02.api.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "flight")
@Data
public class FlightConfig {

    private List<String> types;

}