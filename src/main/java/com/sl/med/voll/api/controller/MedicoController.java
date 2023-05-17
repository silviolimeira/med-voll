package com.sl.med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sl.med.voll.api.domain.medico.DadosAtualizacaoMedico;
import com.sl.med.voll.api.domain.medico.DadosCadastroMedico;
import com.sl.med.voll.api.domain.medico.DadosDetalhamentoMedico;
import com.sl.med.voll.api.domain.medico.DadosListagemMedico;
import com.sl.med.voll.api.domain.medico.Medico;
import com.sl.med.voll.api.domain.medico.MedicoRepository;

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
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
		var medico = repository.save(new Medico(dados));
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
		var page = repository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);
		return ResponseEntity.ok(page);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		medico.excluir();
		
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}
	
	
}
