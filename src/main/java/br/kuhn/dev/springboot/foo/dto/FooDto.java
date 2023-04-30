package br.kuhn.dev.springboot.foo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import br.kuhn.dev.springboot._common.dto.BaseDto;
import br.kuhn.dev.springboot.foo.entity.FooTypeEnum;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class FooDto extends BaseDto {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Select a valid type")
    private FooTypeEnum type;
}
