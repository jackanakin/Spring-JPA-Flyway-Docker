package br.kuhn.dev.springboot.foo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.request.RequestDocumentation.*;

import java.util.Optional;
import java.util.UUID;

import br.kuhn.dev.springboot._common.BaseControllerTest;
import br.kuhn.dev.springboot.foo.dto.FooDto;
import br.kuhn.dev.springboot.foo.entity.Foo;
import br.kuhn.dev.springboot.foo.entity.FooTypeEnum;
import br.kuhn.dev.springboot.foo.service.FooService;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@ComponentScan(basePackages = "br.kuhn.dev.springboot.foo.mapper")
public class FooControllerTest extends BaseControllerTest {

        @MockBean
        FooService mockService;

        FooDto validDto;
        Foo expected;
        UUID id;

        public FooControllerTest() {
                super("/foo", FooDto.class);
        }

        @BeforeEach
        public void setUp() {
                id = UUID.randomUUID();

                validDto = FooDto.builder()
                                .name("Nice Ale")
                                .type(FooTypeEnum.BAR)
                                .build();

                expected = Foo.builder().id(id).name(validDto.getName()).type(validDto.getType()).build();
        }

        @Test
        public void deleteById() throws Exception {
                delete("{id}", id.toString())
                                .andExpect(status().isNoContent())
                                .andDo(document("delete-foo",
                                                pathParameters(
                                                                parameterWithName("id").description(
                                                                                "UUID of desired foo to delete."))));
        }

        @Test
        public void shouldGetById() throws Exception {
                given(mockService.findById(any())).willReturn(Optional.of(expected));

                get("{id}", id.toString())
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", is(expected.getId().toString())))
                                .andExpect(jsonPath("$.name", is(expected.getName())))
                                .andExpect(jsonPath("$.type", is(expected.getType().name())))
                                .andDo(document("get-foo",
                                                pathParameters(
                                                                parameterWithName("id").description(
                                                                                "UUID of desired foo to get.")),
                                                responseFields(
                                                                fields.withPath("id").description("Id of the foo"),
                                                                fields.withPath("name").description("Name of the foo"),
                                                                fields.withPath("type")
                                                                                .description("Type of the foo"))));
        }

        @Test
        public void shouldUpdate() throws Exception {
                String dtoJson = objectMapper.writeValueAsString(validDto);

                given(mockService.update(any(), any())).willReturn(expected);

                put("{id}", dtoJson, id.toString())
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", is(expected.getId().toString())))
                                .andExpect(jsonPath("$.name", is(expected.getName())))
                                .andDo(document("put-foo",
                                                pathParameters(
                                                                parameterWithName("id").description(
                                                                                "UUID of desired foo to update.")),
                                                requestFields(
                                                                fields.withPath("id").ignored(),
                                                                fields.withPath("name").description("Name of the foo"),
                                                                fields.withPath("type").description("Type of the foo")),
                                                responseFields(
                                                                fields.withPath("id").description("Id of the foo"),
                                                                fields.withPath("name").description("Name of the foo"),
                                                                fields.withPath("type")
                                                                                .description("Type of the foo"))));
        }

        @Test
        public void shouldCreate() throws Exception {
                String dtoJson = objectMapper.writeValueAsString(validDto);

                given(mockService.create(any())).willReturn(expected);

                post("", dtoJson)
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id", is(expected.getId().toString())))
                                .andExpect(jsonPath("$.name", is(expected.getName())))
                                .andDo(document("post-foo",
                                                requestFields(
                                                                fields.withPath("id").ignored(),
                                                                fields.withPath("name").description("Name of the foo"),
                                                                fields.withPath("type")
                                                                                .description("Type of the foo"))));
        }
}
