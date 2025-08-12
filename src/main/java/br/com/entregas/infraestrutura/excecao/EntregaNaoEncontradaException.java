package br.com.entregas.infraestrutura.excecao;

import java.util.UUID;

public class EntregaNaoEncontradaException extends RuntimeException 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntregaNaoEncontradaException( UUID id ) 
    {
        super( "Entrega não encontrada para o ID: " + id );
    }

    public EntregaNaoEncontradaException( String mensagem ) 
    {
        super( mensagem );
    }
}
