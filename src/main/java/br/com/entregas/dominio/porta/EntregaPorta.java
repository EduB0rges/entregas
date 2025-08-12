package br.com.entregas.dominio.porta;

import java.util.UUID;

import br.com.entregas.dominio.modelo.EntregaInput;
import br.com.entregas.dominio.modelo.EntregaOutput;

public interface EntregaPorta 
{
	EntregaOutput buscarPorId( UUID id );
	
	EntregaOutput cadastrar( EntregaInput entrega );
}
