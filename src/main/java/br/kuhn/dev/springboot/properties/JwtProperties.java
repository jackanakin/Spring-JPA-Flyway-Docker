package br.kuhn.dev.springboot.properties;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    @Value("${server.secret.key}")
    private String secretKey = null;

    // validity in milliseconds
    @Value("${server.secret.validity}")
    private Long validityInMs = null;

}