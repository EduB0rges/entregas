package br.com.entregas.aplicacao.servico;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
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
	private static final Logger LOGGER = LoggerFactory.getLogger( EntregaPortaServico.class );
	
    private final EntregaRepositorioAdaptador entregaPersistencia;
    
    public EntregaPortaServico( EntregaRepositorioAdaptador entregaPersistencia ) 
    {
        this.entregaPersistencia = entregaPersistencia;
    }
	
	@Override
	@Cacheable(value = "entregasCache", key = "#id")
	public EntregaOutput buscarPorId( String id ) 
	{
		LOGGER.debug( "Buscando entrega com ID: {}", id );
		
		UUID idUuid = null;
		
		try
		{
		    idUuid = UUID.fromString( id );
		}
		catch ( IllegalArgumentException e )
		{
			throw new EntregaNaoEncontradaException( "ID inválido: " + id );
		}
		
		if ( idUuid == null ) 
			throw new EntregaNaoEncontradaException( "ID inválido: " + id );
		
	    return entregaPersistencia.buscarPorId( idUuid )
	            .orElseThrow( ( ) -> new EntregaNaoEncontradaException( id ) );
	}
	
	@Override
    @Transactional
    public EntregaOutput cadastrar( EntregaInput entrega ) 
	{
		LOGGER.debug( "Cadastrando nova entrega: {}", entrega );
		
        EntregaInput input = entrega.nova( entrega.id(),
        		      entrega.quantidadePacotes( ), 
        		      entrega.dataLimiteEntrega( ),
        		      entrega.nomeCliente( ),
        		      entrega.cpfCliente( ),
        		      entrega.endereco( ) );
        
        return entregaPersistencia.salvar( input );
    }
	
	@Override
    @Transactional
    @CacheEvict(value = "entregas", key = "#id")
    public EntregaOutput atualizar( String id, EntregaInput entrega ) 
    {
		LOGGER.debug( "Atualizando entrega com ID: {}, dados: {}", id, entrega );
		
		UUID idUuid = null;
		
		try
		{
			idUuid = UUID.fromString( id );
		}
		catch ( IllegalArgumentException e )
		{
			throw new EntregaNaoEncontradaException( "ID inválido: " + id );
		}
		
		if ( idUuid == null ) 
			throw new EntregaNaoEncontradaException( "ID inválido: " + id );
				
        if( !entregaPersistencia.existePorId( idUuid ) ) 
            throw new EntregaNaoEncontradaException( idUuid.toString() );

        EntregaInput input = entrega.nova( id, 
        	  entrega.quantidadePacotes( ), 
  		      entrega.dataLimiteEntrega( ),
  		      entrega.nomeCliente( ),
  		      entrega.cpfCliente( ),
  		      entrega.endereco( ) );
        
        return entregaPersistencia.salvar( input );
    }

    @Override
    @Transactional
    @CacheEvict(value = "entregas", key = "#id")
    public void deletar( String id ) 
    {
    	LOGGER.debug( "Deletando entrega com ID: {}", id );
    	
    	UUID idUuid = null;
    	
    	try
		{
			idUuid = UUID.fromString( id );
		}
		catch ( IllegalArgumentException e )
		{
			throw new EntregaNaoEncontradaException( "ID inválido: " + id );
		}
        
        entregaPersistencia.deletar( idUuid );
    }
}