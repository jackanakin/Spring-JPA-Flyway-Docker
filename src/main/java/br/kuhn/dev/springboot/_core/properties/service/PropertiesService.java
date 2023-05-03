package br.kuhn.dev.springboot._core.properties.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@Service
public class PropertiesService {
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

    @Value("${server.servlet.context-path}")
    private String contextPath;
}
