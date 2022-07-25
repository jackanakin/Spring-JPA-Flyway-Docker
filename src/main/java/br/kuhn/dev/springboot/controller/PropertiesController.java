package br.kuhn.dev.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
public class PropertiesController {
    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.flyway.enabled}")
    private String flywayEnabled;

    @Value("${logging.level.root}")
    private String loggingLevel;
    
    @GetMapping("/")
    public String get() {
        StringBuilder applicationProperties = new StringBuilder();
        applicationProperties.append(serverPort).append(";").append(applicationName)
                .append(";").append(datasourceUrl).append(";")
                .append(flywayEnabled).append(";").append(flywayEnabled).append(";")
                .append(loggingLevel).append(";");

        System.out.println(applicationProperties);
        return applicationProperties.toString();
    }
}
