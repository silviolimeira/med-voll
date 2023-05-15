package com.sl.med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sl.med.voll.api.medico.DadosAtualizacaoMedico;
import com.sl.med.voll.api.medico.DadosCadastroMedico;
import com.sl.med.voll.api.medico.DadosListagemMedico;
import com.sl.med.voll.api.medico.Medico;
import com.sl.med.voll.api.medico.MedicoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
		repository.save(new Medico(dados));
	}
	
	@GetMapping
	public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
		return repository.findAll(pageable).map(DadosListagemMedico::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		
		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		
	}

}
