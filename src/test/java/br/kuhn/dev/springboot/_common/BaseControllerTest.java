package br.kuhn.dev.springboot._common;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.kuhn.dev.springboot._core.security.service.AuthenticationService;
import br.kuhn.dev.springboot._core.security.service.JwtTokenAuthenticationFilterService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public abstract class BaseControllerTest {
        private String username = "admin";
        private String password = "password";

        @Autowired
        protected MockMvc mockMvc;

        @Autowired
        protected ObjectMapper objectMapper;

        @Autowired
        private AuthenticationService authenticationService;

        protected ResultActions delete(String uri) throws Exception {
                return mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION,
                                                JwtTokenAuthenticationFilterService.HEADER_PREFIX
                                                                + authenticationService.authenticate(username,
                                                                                password)));
        }

        protected ResultActions get(String uri) throws Exception {
                return mockMvc.perform(MockMvcRequestBuilders.get(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION,
                                                JwtTokenAuthenticationFilterService.HEADER_PREFIX
                                                                + authenticationService.authenticate(username,
                                                                                password)));
        }

        protected ResultActions put(String uri, String json) throws Exception {
                return mockMvc.perform(MockMvcRequestBuilders.put(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION,
                                                JwtTokenAuthenticationFilterService.HEADER_PREFIX
                                                                + authenticationService.authenticate(username,
                                                                                password))
                                .content(json));
        }

        protected ResultActions post(String uri, String json) throws Exception {
                return mockMvc.perform(MockMvcRequestBuilders.post(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION,
                                                JwtTokenAuthenticationFilterService.HEADER_PREFIX
                                                                + authenticationService.authenticate(username,
                                                                                password))
                                .content(json));
        }
}
