package com.sl.med.voll.api.paciente;

import com.sl.med.voll.api.endereco.Endereco;

public record DadosListagemPaciente(Long id, String nome, String email, String telefone, String cpf, Endereco endereco)  {
	public DadosListagemPaciente(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
	}
}