package br.kuhn.dev.springboot._common.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseDto implements Serializable {
    @Null
    private UUID id;
}
