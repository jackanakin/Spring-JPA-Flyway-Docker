package br.kuhn.dev.springboot._core.registration.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import br.kuhn.dev.springboot._common.dto.BaseDto;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class RegistrationDto extends BaseDto {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Enter a valid e-mail")
    private String email;

    @Size(min = 8, message = "Use a strong password")
    private String password;

}
