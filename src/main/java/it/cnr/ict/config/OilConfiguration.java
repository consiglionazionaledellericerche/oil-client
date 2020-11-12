package it.cnr.ict.config;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import it.cnr.ict.repository.Oil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "oil.url")
@EnableConfigurationProperties(OilConfigurationProperties.class)
public class OilConfiguration {

    @Bean
    public Oil oil(OilConfigurationProperties ocp) { ;
        return Feign.builder()
                .requestInterceptor(new BasicAuthRequestInterceptor(ocp.getUsername(), ocp.getPassword()))
                .encoder(new FormEncoder(new GsonEncoder()))
                .decoder(new GsonDecoder())
                .target(Oil.class, ocp.getUrl());

    }

}
