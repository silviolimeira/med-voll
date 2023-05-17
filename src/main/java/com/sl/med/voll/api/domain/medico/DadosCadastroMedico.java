package com.sl.med.voll.api.domain.medico;

import com.sl.med.voll.api.domain.endereco.DadosEndereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
		@NotBlank(message = "{nome.obrigatorio}") String nome, 
		@NotBlank(message = "Formato do email inválido") @Email String email,
		@NotBlank String telefone,
		@NotBlank @Pattern(regexp = "\\d{4,6}") String crm, @NotNull Especialidade especialidade,
		@Valid DadosEndereco endereco) {

}
