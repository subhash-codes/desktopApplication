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
        config.put("cloud_name","di0n7er1i");
        config.put("api_key","561346126352232");
        config.put("api_secret","PT6Eh-zXj7Q6tSB8ROye0fR6Mqc");
        config.put("secure",true);
        return new Cloudinary(config);
    }
}
