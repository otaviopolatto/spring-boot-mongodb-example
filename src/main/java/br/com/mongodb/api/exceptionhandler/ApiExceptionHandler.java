package br.com.mongodb.api.exceptionhandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.mongodb.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.mongodb.api.domain.exception.NegocioException;
import io.swagger.v3.oas.annotations.Hidden;

import lombok.AllArgsConstructor;

@Hidden
@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final MessageSource messageSource;
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos estão inválidos");
        problemDetail.setType(URI.create("https://mongodb-api.com.br/erros/campos-invalidos"));

        Map<String, String> fields = ex.getBindingResult().getAllErrors()
                .stream()
                .collect(Collectors.toMap(objectError -> ( (FieldError) objectError).getField(),
                        objectError -> (messageSource.getMessage(objectError, LocaleContextHolder.getLocale()))));

        problemDetail.setProperty("fields", fields);

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
        
    }
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ProblemDetail handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException e) {

		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
		problemDetail.setTitle(e.getMessage());
		problemDetail.setType(URI.create("https://mongodb-api.com.br/erros/nao-encontrado"));

		return problemDetail;

	}
	
	@ExceptionHandler(NegocioException.class)
	public ProblemDetail handleEntidadeNaoEncontrada(NegocioException e) {

		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setTitle(e.getMessage());
		problemDetail.setType(URI.create("https://customerorders.dataflowtechnology.com.br/erros/regra-de-negocio"));

		return problemDetail;

	}
	
	

}
