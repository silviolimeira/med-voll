package com.sl.med.voll.api.medico;

import com.sl.med.voll.api.dto.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {

}
	