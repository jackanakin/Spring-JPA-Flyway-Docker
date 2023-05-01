package br.kuhn.dev.springboot.foo.controller;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.hamcrest.core.Is.is;

import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.UUID;

import br.kuhn.dev.springboot._common.BaseControllerTest;
import br.kuhn.dev.springboot.foo.dto.FooDto;
import br.kuhn.dev.springboot.foo.entity.Foo;
import br.kuhn.dev.springboot.foo.entity.FooTypeEnum;
import br.kuhn.dev.springboot.foo.service.FooService;

public class FooControllerTest extends BaseControllerTest {

    @MockBean
    FooService mockService;

    FooDto validDto;
    Foo expected;
    UUID id;

    @Before
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
        delete("/foo/" + id.toString())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetById() throws Exception {
        given(mockService.findById(any())).willReturn(Optional.of(expected));

        get("/foo/" + id.toString())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(expected.getId().toString())))
                .andExpect(jsonPath("$.name", is(expected.getName())));
    }

    @Test
    public void shouldUpdate() throws Exception {
        String dtoJson = objectMapper.writeValueAsString(validDto);

        given(mockService.update(any(), any())).willReturn(expected);

        put("/foo/" + id.toString(), dtoJson)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(expected.getId().toString())))
                .andExpect(jsonPath("$.name", is(expected.getName())));
    }

    @Test
    public void shouldCreate() throws Exception {
        String dtoJson = objectMapper.writeValueAsString(validDto);

        given(mockService.create(any())).willReturn(expected);

        post("/foo", dtoJson)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(expected.getId().toString())))
                .andExpect(jsonPath("$.name", is(expected.getName())));
    }
}
