package br.com.entregas.dominio.modelo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoInput
(
    @NotBlank(message = "O logradouro é obrigatório")
    String logradouro,
    
    @NotBlank(message = "O número é obrigatório")
    String numero,
    
    String complemento,
    
    @NotBlank(message = "O bairro é obrigatório")
    String bairro,
    
    @NotBlank(message = "A cidade é obrigatória")
    String cidade,
    
    @NotBlank(message = "O estado é obrigatório")
    @Pattern(regexp = "[A-Z]{2}", message = "O estado deve ser uma sigla de duas letras maiúsculas")
    String estado,
    
    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter 8 dígitos numéricos")
    String cep
) {
    // Construtor para criar um endereço
    public static EnderecoInput novo(
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String estado,
            String cep) {
        return new EnderecoInput(
            logradouro,
            numero,
            complemento,
            bairro,
            cidade,
            estado,
            cep
        );
    }

    // Método para atualizar um endereço 
    public EnderecoInput atualizar(
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String estado,
            String cep) {
        return new EnderecoInput(
            logradouro,
            numero,
            complemento,
            bairro,
            cidade,
            estado,
            cep
        );
    }
}
