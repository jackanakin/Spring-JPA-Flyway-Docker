package br.kuhn.dev.springboot._core.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import br.kuhn.dev.springboot._common.dto.BaseDto;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto extends BaseDto {
    private String username;
    private String token;
}
