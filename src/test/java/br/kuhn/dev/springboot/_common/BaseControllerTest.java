package br.kuhn.dev.springboot._common;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.StringUtils;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.kuhn.dev.springboot._core.security.service.AuthenticationService;
import br.kuhn.dev.springboot._core.security.service.JwtTokenAuthenticationFilterService;

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
        private AuthenticationService authenticationService;

        private String username = "admin";
        private String password = "password";

        protected ConstrainedFields fields;

        public BaseControllerTest(Class<?> entityClass) {
                fields = new ConstrainedFields(entityClass);
        }

        protected ResultActions delete(String uri, java.lang.Object... urlVariables) throws Exception {
                return mockMvc.perform(RestDocumentationRequestBuilders.delete(uri, urlVariables)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION,
                                                JwtTokenAuthenticationFilterService.HEADER_PREFIX
                                                                + authenticationService.authenticate(username,
                                                                                password)));
        }

        protected ResultActions get(String uri, java.lang.Object... urlVariables) throws Exception {
                return mockMvc.perform(RestDocumentationRequestBuilders.get(uri, urlVariables)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION,
                                                JwtTokenAuthenticationFilterService.HEADER_PREFIX
                                                                + authenticationService.authenticate(username,
                                                                                password)));
        }

        protected ResultActions put(String uri, String json, java.lang.Object... urlVariables) throws Exception {
                return mockMvc.perform(RestDocumentationRequestBuilders.put(uri, urlVariables)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION,
                                                JwtTokenAuthenticationFilterService.HEADER_PREFIX
                                                                + authenticationService.authenticate(username,
                                                                                password))
                                .content(json));
        }

        protected ResultActions post(String uri, String json, java.lang.Object... urlVariables) throws Exception {
                return mockMvc.perform(RestDocumentationRequestBuilders.post(uri, urlVariables)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION,
                                                JwtTokenAuthenticationFilterService.HEADER_PREFIX
                                                                + authenticationService.authenticate(username,
                                                                                password))
                                .content(json));
        }
}
