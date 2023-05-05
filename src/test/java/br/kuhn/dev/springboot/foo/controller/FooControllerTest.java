package br.kuhn.dev.springboot.foo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

                FooDto validResponseDto = FooDto.builder()
                                .id(UUID.randomUUID())
                                .name("Nice Ale")
                                .type(FooTypeEnum.BAR)
                                .build();

                validPage = new GenericPage<FooDto>(List.of(validResponseDto), 0, 10, 1L);

                expected = Foo.builder().id(id).name(validCreateUpdateDto.getName())
                                .type(validCreateUpdateDto.getType()).build();
        }

        @Test
        void should_getPage() throws Exception {
                given(mockService.findPageable(anyInt(), anyInt(), any())).willReturn(validPage);

                get("", id.toString())
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
        void should_create() throws Exception {
                String dtoJson = objectMapper.writeValueAsString(validCreateUpdateDto);

                given(mockService.create(any())).willReturn(expected);

                post("", dtoJson)
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id", is(expected.getId().toString())))
                                .andExpect(jsonPath("$.name", is(expected.getName())));
        }
}
