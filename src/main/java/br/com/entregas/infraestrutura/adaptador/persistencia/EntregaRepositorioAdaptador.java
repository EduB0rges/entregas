package br.com.entregas.infraestrutura.adaptador.persistencia;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.entregas.dominio.modelo.EntregaInput;
import br.com.entregas.dominio.modelo.EntregaOutput;
import br.com.entregas.dominio.porta.EntregaPersistenciaPorta;
import br.com.entregas.infraestrutura.adaptador.persistencia.entidade.EntregaEntidade;
import br.com.entregas.infraestrutura.adaptador.persistencia.mapper.EntregaMapper;
import br.com.entregas.infraestrutura.adaptador.persistencia.repositorio.EntregaJpaRepositorio;

@Repository
public class EntregaRepositorioAdaptador implements EntregaPersistenciaPorta 
{    
    private final EntregaJpaRepositorio entregaRepository;
    private final EntregaMapper         entregaMapper;
    
    public EntregaRepositorioAdaptador( EntregaJpaRepositorio entregaRepository, EntregaMapper entregaMapper ) 
    {
		this.entregaRepository = entregaRepository;
		this.entregaMapper     = entregaMapper;
	}

    @Override
    public Optional<EntregaOutput> buscarPorId( UUID id ) 
    {
        return entregaRepository.findById( id )
                .map( entregaMapper::paraOutput );
    }

	@Override
	public EntregaOutput salvar( EntregaInput entrega ) 
	{
		EntregaEntidade entidade = entregaMapper.paraEntidade( entrega );
		
		entidade = entregaRepository.save( entidade );
		
		return entregaMapper.paraOutput( entidade );
	}
	
	@Override
    public void deletar( UUID id ) 
	{
		entregaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId( UUID id ) 
    {    	
        return entregaRepository.existsById( id );
    }
}