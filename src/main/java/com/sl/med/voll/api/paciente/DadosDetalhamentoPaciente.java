package com.sl.med.voll.api.paciente;

import com.sl.med.voll.api.endereco.Endereco;

public record DadosDetalhamentoPaciente(String nome, String email, String telefone, String cpf, Endereco endereco) { 
    public DadosDetalhamentoPaciente(Paciente paciente) { 
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco()); 
    }
} 