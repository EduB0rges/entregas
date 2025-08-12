package br.com.entregas.dominio.modelo;

import java.time.LocalDateTime;
import java.util.UUID;

public record EntregaOutput( UUID id, Integer quantidadePacotes, LocalDateTime dataLimiteEntrega, String nomeCliente, String cpfCliente, EnderecoOutput endereco ) 
{

}