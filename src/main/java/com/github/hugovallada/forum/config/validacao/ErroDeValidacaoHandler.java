package com.github.hugovallada.forum.config.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception){
        List<ErroDeFormularioDto> erros = exception.getBindingResult()
                .getFieldErrors().stream().map(error -> {
                    String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
                   return new ErroDeFormularioDto(error.getField(), message);
                }).collect(Collectors.toList());


        return erros;
    }

}
