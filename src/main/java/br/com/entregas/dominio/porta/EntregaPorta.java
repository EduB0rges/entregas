package br.com.entregas.dominio.porta;

import br.com.entregas.dominio.modelo.EntregaInput;
import br.com.entregas.dominio.modelo.EntregaOutput;

public interface EntregaPorta 
{
	EntregaOutput buscarPorId( String id );
	
	EntregaOutput cadastrar( EntregaInput entrega );
	
	EntregaOutput atualizar( String id, EntregaInput entrega );
	
	void deletar( String id );
}
