package br.com.entregas.dominio.modelo;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EntregaInput
(
    String id,
    
    @NotNull( message = "A quantidade de pacotes é obrigatória" )
    @Min( value = 1, message = "A quantidade de pacotes deve ser maior que zero" )
    Integer quantidadePacotes,
    
    @NotNull( message = "A data limite de entrega é obrigatória" )
    @Future( message = "A data limite deve ser uma data futura" )
    LocalDateTime dataLimiteEntrega,
    
    @NotBlank( message = "O nome do cliente é obrigatório" )
    String nomeCliente,
    
    @NotBlank( message = "O CPF do cliente é obrigatório" )
    String cpfCliente,
    
    @NotNull( message = "O endereço é obrigatório" )
    EnderecoInput endereco
) {
    // Construtor compacto para criar nova entrega
    public EntregaInput nova( String id,
            Integer quantidadePacotes,
            LocalDateTime dataLimiteEntrega,
            String nomeCliente,
            String cpfCliente,
            EnderecoInput endereco) {
        return new EntregaInput(
            id,
            quantidadePacotes,
            dataLimiteEntrega,
            nomeCliente,
            cpfCliente,
            endereco
        );
    }

    // Método para atualizar uma entrega existente
    public EntregaInput atualizar(
            Integer quantidadePacotes,
            LocalDateTime dataLimiteEntrega,
            String nomeCliente,
            String cpfCliente,
            EnderecoInput endereco ) {
        return new EntregaInput(
            this.id,
            quantidadePacotes,
            dataLimiteEntrega,
            nomeCliente,
            cpfCliente,
            endereco
        );
    }
}