package com.sl.med.voll.api.domain.paciente;

import com.sl.med.voll.api.domain.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(@NotNull Long id, String nome, String telefone, String cpf, DadosEndereco endereco) {

}
