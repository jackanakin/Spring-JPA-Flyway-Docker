package br.kuhn.dev.springboot._common.dto;

import java.util.UUID;

import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseDto {
    @Null
    private UUID id;
}
