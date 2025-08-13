package br.com.entregas.infraestrutura.adaptador.persistencia.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.entregas.dominio.modelo.EnderecoOutput;
import br.com.entregas.dominio.modelo.EntregaInput;
import br.com.entregas.dominio.modelo.EntregaOutput;
import br.com.entregas.infraestrutura.adaptador.persistencia.entidade.EnderecoEntregaEntidade;
import br.com.entregas.infraestrutura.adaptador.persistencia.entidade.EntregaEntidade;

@Component
public class EntregaMapper 
{    
    public EntregaOutput paraOutput( EntregaEntidade entidade ) 
    {
        EnderecoEntregaEntidade enderecoEntidade = entidade.getEnderecoEntrega( );
        
        EnderecoOutput endereco = new EnderecoOutput(
            enderecoEntidade.getLogradouro(),
            enderecoEntidade.getNumero(),
            enderecoEntidade.getComplemento(),
            enderecoEntidade.getBairro(),
            enderecoEntidade.getCidade(),
            enderecoEntidade.getEstado(),
            enderecoEntidade.getCep()
        );
        
        return new EntregaOutput(
            entidade.getId().toString(),
            entidade.getQuantidadePacotes(),
            entidade.getDataLimiteEntrega(),
            entidade.getNomeCliente(),
            entidade.getCpfCliente(),
            endereco            
        );
    }
    
    public EntregaEntidade paraEntidade( EntregaInput entrega ) 
	{
		EnderecoEntregaEntidade enderecoEntidade = new EnderecoEntregaEntidade();
		enderecoEntidade.setLogradouro( entrega.endereco().logradouro() );
		enderecoEntidade.setNumero( entrega.endereco().numero() );
		enderecoEntidade.setComplemento( entrega.endereco().complemento() );
		enderecoEntidade.setBairro( entrega.endereco().bairro() );
		enderecoEntidade.setCidade( entrega.endereco().cidade() );
		enderecoEntidade.setEstado( entrega.endereco().estado() );
		enderecoEntidade.setCep( entrega.endereco().cep() );
		
		EntregaEntidade entidade = new EntregaEntidade();
		entidade.setId( entrega.id() == null ? UUID.randomUUID() : UUID.fromString( entrega.id() ) );
		entidade.setQuantidadePacotes( entrega.quantidadePacotes() );
		entidade.setNomeCliente( entrega.nomeCliente() );
		entidade.setCpfCliente( entrega.cpfCliente() );
		entidade.setDataLimiteEntrega( entrega.dataLimiteEntrega() );
		entidade.setEnderecoEntrega( enderecoEntidade );
		
		return entidade;
	}
}
