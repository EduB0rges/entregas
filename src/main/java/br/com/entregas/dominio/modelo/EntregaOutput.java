package br.com.entregas.dominio.modelo;

import java.time.LocalDateTime;

public record EntregaOutput( String id, Integer quantidadePacotes, LocalDateTime dataLimiteEntrega, String nomeCliente, String cpfCliente, EnderecoOutput endereco ) 
{

}