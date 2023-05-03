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
public class AuthRequestDto extends BaseDto {
    private static final long serialVersionUID = -6986746375915710855L;
    private String username;
    private String password;
}