package com.sl.med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sl.med.voll.api.paciente.DadosCadastroPaciente;
import com.sl.med.voll.api.paciente.Paciente;
import com.sl.med.voll.api.paciente.PacienteRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;

	@PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
        repository.save(new Paciente(dados));
    }
}