package br.com.entregas.infraestrutura.excecao;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
    @ExceptionHandler( EntregaNaoEncontradaException.class )
    public ResponseEntity<ErroResponse> handleEntregaNaoEncontrada( EntregaNaoEncontradaException ex ) 
    {
        ErroResponse erro = new ErroResponse(
                HttpStatus.NOT_FOUND.value( ),
                ex.getMessage( ),
                LocalDateTime.now( )
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( erro );
    }

    @ExceptionHandler( Exception.class )
    public ResponseEntity<ErroResponse> handleGenerico( Exception ex ) 
    {
        ErroResponse erro = new ErroResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value( ),
                "Erro interno do servidor",
                LocalDateTime.now( )
        );
        return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( erro );
    }
}