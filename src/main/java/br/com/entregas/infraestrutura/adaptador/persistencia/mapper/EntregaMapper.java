package br.com.entregas.infraestrutura.adaptador.persistencia.mapper;

import org.springframework.stereotype.Component;

import br.com.entregas.dominio.modelo.EnderecoOutput;
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
            entidade.getId(),
            entidade.getQuantidadePacotes(),
            entidade.getDataLimiteEntrega(),
            entidade.getNomeCliente(),
            entidade.getCpfCliente(),
            endereco            
        );
    }
}
