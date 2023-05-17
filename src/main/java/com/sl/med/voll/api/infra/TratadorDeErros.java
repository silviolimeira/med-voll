package com.sl.med.voll.api.infra;

import java.util.List;

import org.hibernate.LazyInitializationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

//@ControllerAdvice
//@ResponseStatus
@RestControllerAdvice
@Slf4j
public class TratadorDeErros {

	@ExceptionHandler(value = {EntityNotFoundException.class})
	public ResponseEntity<ErrorMessage> trataError404() {
		log.info("Entity Not Foud!");
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, "Entity Not Foud!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
	@ExceptionHandler(value = {LazyInitializationException.class})
	public ResponseEntity<ErrorMessage> trataErrorLazyInitialization() {
		log.info("Entity Not Foud!");
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, "Entity Not Foud!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
	@ExceptionHandler(value = {HttpMessageNotReadableException.class})
	public ResponseEntity<ErrorMessage> httpMessageNotReadableException(HttpMessageNotReadableException e) {
		log.info(e.getMessage());
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<List<DadosErroValidacao>> trataError400(MethodArgumentNotValidException e) {
		log.info("Bad Request!");
		var errors = e.getFieldErrors();
		
		
		//ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, "Bad Request!");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.stream().map(DadosErroValidacao::new).toList());
	}
	
	private record DadosErroValidacao(String campo, String mensagem) {
		public DadosErroValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
	
	
}
