package com.sl.med.voll.api.domain.medico;

import com.sl.med.voll.api.domain.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {

}
