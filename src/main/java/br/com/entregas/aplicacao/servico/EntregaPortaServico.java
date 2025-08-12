package br.com.entregas.aplicacao.servico;

import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.entregas.dominio.modelo.EntregaInput;
import br.com.entregas.dominio.modelo.EntregaOutput;
import br.com.entregas.dominio.porta.EntregaPorta;
import br.com.entregas.infraestrutura.adaptador.persistencia.EntregaRepositorioAdaptador;
import br.com.entregas.infraestrutura.excecao.EntregaNaoEncontradaException;

@Service
public class EntregaPortaServico implements EntregaPorta
{
    private final EntregaRepositorioAdaptador entregaPersistencia;
    
    public EntregaPortaServico( EntregaRepositorioAdaptador entregaPersistencia ) 
    {
        this.entregaPersistencia = entregaPersistencia;
    }
	
	@Override
	@Cacheable(value = "entregasCache", key = "#id")
	public EntregaOutput buscarPorId( UUID id ) 
	{
	    return entregaPersistencia.buscarPorId( id )
	            .orElseThrow(() -> new EntregaNaoEncontradaException( id ) );
	}
	
	@Override
    @Transactional
    public EntregaOutput cadastrar( EntregaInput entrega ) 
	{
        entrega.nova( entrega.quantidadePacotes( ), 
        		      entrega.dataLimiteEntrega( ),
        		      entrega.nomeCliente( ),
        		      entrega.cpfCliente( ),
        		      entrega.endereco( ) );
        
        return entregaPersistencia.salvar( entrega );
    }
}