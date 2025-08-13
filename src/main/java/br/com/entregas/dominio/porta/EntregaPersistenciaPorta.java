package br.com.entregas.dominio.porta;

import java.util.Optional;
import java.util.UUID;

import br.com.entregas.dominio.modelo.EntregaInput;
import br.com.entregas.dominio.modelo.EntregaOutput;

public interface EntregaPersistenciaPorta 
{
    Optional<EntregaOutput> buscarPorId( UUID id );
    
    EntregaOutput salvar( EntregaInput entrega );
    
    void deletar( UUID id );

    boolean existePorId( UUID id );
}