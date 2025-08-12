package br.com.entregas.infraestrutura.excecao;

import java.time.LocalDateTime;

public record ErroResponse( int status, String mensagem, LocalDateTime timestamp ) 
{
}