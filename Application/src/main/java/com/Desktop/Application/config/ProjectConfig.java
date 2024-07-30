package com.Desktop.Application.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig {

    @Bean
    public Cloudinary geCloudinary(){

        Map config = new HashMap();
        config.put("cloud_name","dvfj3a3xy");
        config.put("api_key","394558672423462");
        config.put("api_secret","PUlJFvU6aWLLtKwqd_zckPp4U0Q");
        config.put("secure",true);
        return new Cloudinary(config);
    }
}
