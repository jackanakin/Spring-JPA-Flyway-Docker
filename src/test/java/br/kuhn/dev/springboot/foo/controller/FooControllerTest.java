package br.kuhn.dev.springboot.foo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.kuhn.dev.springboot._common.BaseControllerTest;
import br.kuhn.dev.springboot._common.repository.GenericPage;
import br.kuhn.dev.springboot.foo.dto.FooDto;
import br.kuhn.dev.springboot.foo.entity.Foo;
import br.kuhn.dev.springboot.foo.entity.FooTypeEnum;
import br.kuhn.dev.springboot.foo.service.FooService;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@ComponentScan(basePackages = "br.kuhn.dev.springboot.foo.mapper")
class FooControllerTest extends BaseControllerTest {

        @MockBean
        FooService mockService;

        UUID id;
        Foo expected;
        FooDto validCreateUpdateDto;
        GenericPage<FooDto> validPage;
        FooDto validResponseDto;

        FooControllerTest() {
                super("/foo", FooDto.class);
        }

        @BeforeEach
        void setUp() {
                id = UUID.randomUUID();

                validCreateUpdateDto = FooDto.builder()
                                .name("Nice Ale")
                                .type(FooTypeEnum.BAR)
                                .build();

                validResponseDto = FooDto.builder()
                                .id(UUID.randomUUID())
                                .name("Nice Ale")
                                .type(FooTypeEnum.BAR)
                                .build();

                expected = Foo.builder().id(id).name(validCreateUpdateDto.getName())
                                .type(validCreateUpdateDto.getType()).build();
        }

        @Test
        void should_getPageNumber2AndSize1() throws Exception {
                validPage = new GenericPage<FooDto>(
                                List.of(validResponseDto, validResponseDto, validResponseDto, validResponseDto), 2, 1,
                                4L);

                given(mockService.findPageable(eq(validPage.getPageable().getPageNumber()),
                                eq(validPage.getPageable().getPageSize()), any())).willReturn(validPage);

                get("?pageSize=1&pageNumber=2")
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.content[0].id",
                                                is(validPage.getContent().get(0).getId().toString())))
                                .andExpect(jsonPath("$.numberOfElements", is(validPage.getNumberOfElements())));
        }

        @Test
        void should_getPageSize1() throws Exception {
                validPage = new GenericPage<FooDto>(
                                List.of(validResponseDto, validResponseDto, validResponseDto, validResponseDto), 0, 1,
                                4L);

                given(mockService.findPageable(eq(validPage.getPageable().getPageNumber()),
                                eq(validPage.getPageable().getPageSize()), any())).willReturn(validPage);

                get("?pageSize=1")
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.content[0].id",
                                                is(validPage.getContent().get(0).getId().toString())))
                                .andExpect(jsonPath("$.numberOfElements", is(validPage.getNumberOfElements())));
        }

        @Test
        void should_getPage() throws Exception {
                validPage = new GenericPage<FooDto>(List.of(validResponseDto), 0, 5, 1L);

                given(mockService.findPageable(eq(validPage.getPageable().getPageNumber()),
                                eq(validPage.getPageable().getPageSize()), any())).willReturn(validPage);

                get("")
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.content[0].id",
                                                is(validPage.getContent().get(0).getId().toString())))
                                .andExpect(jsonPath("$.numberOfElements", is(validPage.getNumberOfElements())));
        }

        @Test
        void should_deleteById() throws Exception {
                delete("{id}", id.toString())
                                .andExpect(status().isNoContent());
        }

        @Test
        void should_getById() throws Exception {
                given(mockService.findById(any())).willReturn(Optional.of(expected));

                get("{id}", id.toString())
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", is(expected.getId().toString())))
                                .andExpect(jsonPath("$.name", is(expected.getName())))
                                .andExpect(jsonPath("$.type", is(expected.getType().name())));
        }

        @Test
        void should_update() throws Exception {
                String dtoJson = objectMapper.writeValueAsString(validCreateUpdateDto);

                given(mockService.update(any(), any())).willReturn(expected);

                put("{id}", dtoJson, id.toString())
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", is(expected.getId().toString())))
                                .andExpect(jsonPath("$.name", is(expected.getName())));
        }

        @Test
        void should_NotCreate_empty_type() throws Exception {
                String dtoJson = FooDto.builder()
                                .name("awdawd")
                                .build().toString();

                post("", dtoJson)
                                .andExpect(status().isBadRequest());
        }

        @Test
        void should_NotCreate_empty_name() throws Exception {
                String dtoJson = FooDto.builder()
                                .name("")
                                .type(FooTypeEnum.BAR)
                                .build().toString();

                post("", dtoJson)
                                .andExpect(status().isBadRequest());
        }

        @Test
        void should_create() throws Exception {
                String dtoJson = objectMapper.writeValueAsString(validCreateUpdateDto);

                given(mockService.create(any())).willReturn(expected);

                post("", dtoJson)
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id", is(expected.getId().toString())))
                                .andExpect(jsonPath("$.name", is(expected.getName())));
        }
}
