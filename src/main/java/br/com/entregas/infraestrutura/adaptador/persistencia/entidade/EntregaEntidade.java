package br.com.entregas.infraestrutura.adaptador.persistencia.entidade;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EntregaEntidade 
{    
    @Id
    private UUID id;
    
    @Column(nullable = false)
    private Integer quantidadePacotes;
    
    @Column(nullable = false)
    private String nomeCliente;
    
    @Column(nullable = false)
    private String cpfCliente;
    
    @Column(nullable = false)
    private LocalDateTime dataLimiteEntrega;
    
    @Column(nullable = false)
    private EnderecoEntregaEntidade enderecoEntrega;

	public UUID getId( ) 
	{
		return id;
	}

	public void setId( UUID id ) 
	{
		this.id = id;
	}

	public Integer getQuantidadePacotes( ) 
	{
		return quantidadePacotes;
	}

	public void setQuantidadePacotes( Integer quantidadePacotes ) 
	{
		this.quantidadePacotes = quantidadePacotes;
	}

	public String getNomeCliente( ) 
	{
		return nomeCliente;
	}

	public void setNomeCliente( String nomeCliente ) 
	{
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCliente( ) 
	{
		return cpfCliente;
	}

	public void setCpfCliente( String cpfCliente ) 
	{
		this.cpfCliente = cpfCliente;
	}

	public LocalDateTime getDataLimiteEntrega( ) 
	{
		return dataLimiteEntrega;
	}

	public void setDataLimiteEntrega( LocalDateTime dataLimiteEntrega ) 
	{
		this.dataLimiteEntrega = dataLimiteEntrega;
	}

	public EnderecoEntregaEntidade getEnderecoEntrega( ) 
	{
		return enderecoEntrega;
	}

	public void setEnderecoEntrega( EnderecoEntregaEntidade enderecoEntrega ) 
	{
		this.enderecoEntrega = enderecoEntrega;
	}
}