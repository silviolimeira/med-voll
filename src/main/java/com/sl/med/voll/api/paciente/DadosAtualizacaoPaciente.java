package com.sl.med.voll.api.paciente;

import com.sl.med.voll.api.endereco.DadosEndereco;
import com.sl.med.voll.api.endereco.Endereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoPaciente(@NotNull Long id, String nome, String telefone, String cpf, DadosEndereco endereco) {

}
