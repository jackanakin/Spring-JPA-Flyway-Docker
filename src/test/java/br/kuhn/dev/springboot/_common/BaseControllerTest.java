package br.kuhn.dev.springboot._common;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.kuhn.dev.springboot._core.auth.service.AuthService;
import br.kuhn.dev.springboot._core.security.service.JwtTokenAuthenticationFilterService;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
public abstract class BaseControllerTest {

        @Autowired
        protected MockMvc mockMvc;

        @Autowired
        protected ObjectMapper objectMapper;

        @Autowired
        private AuthService authService;

        @Value("${server.servlet.context-path}")
        private String contextPath;

        private String rootUri;
        private String username = "admin";
        private String password = "password";

        protected ConstrainedFields fields;

        public BaseControllerTest(String rootUri, Class<?> dtoClass) {
                this.rootUri = rootUri;
                this.fields = new ConstrainedFields(dtoClass);
        }

        private String uri(String path) {
                if (this.contextPath.endsWith("/")) {
                        return this.contextPath.substring(0, this.contextPath.length() - 1) + this.rootUri + "/" + path;
                }

                return this.contextPath + this.rootUri + "/" + path;
        }

        protected ResultActions delete(String path, java.lang.Object... urlVariables) throws Exception {
                return mockMvc.perform(RestDocumentationRequestBuilders.delete(uri(path), urlVariables)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION,
                                                JwtTokenAuthenticationFilterService.HEADER_PREFIX
                                                                + authService.authenticate(username,
                                                                                password)));
        }

        protected ResultActions get(String path, java.lang.Object... urlVariables) throws Exception {
                return mockMvc.perform(RestDocumentationRequestBuilders.get(uri(path), urlVariables)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION,
                                                JwtTokenAuthenticationFilterService.HEADER_PREFIX
                                                                + authService.authenticate(username,
                                                                                password)));
        }

        protected ResultActions put(String path, String json, java.lang.Object... urlVariables) throws Exception {
                return mockMvc.perform(RestDocumentationRequestBuilders.put(uri(path), urlVariables)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION,
                                                JwtTokenAuthenticationFilterService.HEADER_PREFIX
                                                                + authService.authenticate(username,
                                                                                password))
                                .content(json));
        }

        protected ResultActions post(String path, String json, java.lang.Object... urlVariables) throws Exception {
                return mockMvc.perform(RestDocumentationRequestBuilders.post(uri(path), urlVariables)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION,
                                                JwtTokenAuthenticationFilterService.HEADER_PREFIX
                                                                + authService.authenticate(username,
                                                                                password))
                                .content(json));
        }
}
