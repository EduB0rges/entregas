package br.com.entregas.aplicacao.servico;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.entregas.dominio.modelo.EntregaInput;
import br.com.entregas.dominio.modelo.EntregaOutput;
import br.com.entregas.infraestrutura.adaptador.persistencia.EntregaRepositorioAdaptador;
import br.com.entregas.infraestrutura.adaptador.persistencia.mapper.EntregaMapper;
import br.com.entregas.infraestrutura.excecao.EntregaNaoEncontradaException;

class EntregaPortaServicoTest
{
	@Mock
	private EntregaMapper entregaModeloMapper;
	
    @Mock
    private EntregaRepositorioAdaptador entregaPersistencia;

    @InjectMocks
    private EntregaPortaServico entregaPortaServico;

    @BeforeEach
    void setUp( )
    {
        MockitoAnnotations.openMocks( this );
    }

    @Test
    void testBuscarPorIdComIdValido( )
    {
        UUID          id     = UUID.randomUUID( );
        EntregaOutput output = mock( EntregaOutput.class );
        
        when( entregaPersistencia.buscarPorId( id ) ).thenReturn( Optional.of( output ) );

        EntregaOutput result = entregaPortaServico.buscarPorId( id.toString( ) );
        assertEquals( output, result );
    }

    @Test
    void testBuscarPorIdComIdInvalido( )
    {
        String idInvalido = "id_invalido";
        
        assertThrows( EntregaNaoEncontradaException.class, ( ) -> entregaPortaServico.buscarPorId( idInvalido ) );
    }

    @Test
    void testCadastrar( )
    {
        EntregaInput  input  = mock( EntregaInput.class  );
        EntregaOutput output = mock( EntregaOutput.class );

        when( entregaPersistencia.salvar( any( EntregaInput.class ) ) ).thenReturn( output );

        EntregaOutput result = entregaPortaServico.cadastrar( input );
        
        assertEquals( output, result );
    }

    @Test
    void testAtualizarComIdValidoExistente( )
    {
        UUID          id     = UUID.randomUUID( );
        EntregaInput  input  = mock( EntregaInput.class  );
        EntregaOutput output = mock( EntregaOutput.class );
        
        when( entregaPersistencia.existePorId( id ) ).thenReturn( true );
        when( entregaPersistencia.salvar( any( EntregaInput.class ) ) ).thenReturn( output );

        EntregaOutput result = entregaPortaServico.atualizar( id.toString( ), input );
        
        assertEquals( output, result );
    }

    @Test
    void testAtualizarComIdInvalido( )
    {
        String       idInvalido = "id_invalido";
        EntregaInput input      = mock( EntregaInput.class );
        
        assertThrows( EntregaNaoEncontradaException.class, (  ) -> entregaPortaServico.atualizar( idInvalido, input ));
    }

    @Test
    void testAtualizarComIdNaoExistente( )
    {
        UUID         id    = UUID.randomUUID ();
        EntregaInput input = mock( EntregaInput.class );
        
        when( entregaPersistencia.existePorId( id ) ).thenReturn( false );
        
        assertThrows( EntregaNaoEncontradaException.class, ( ) -> entregaPortaServico.atualizar( id.toString( ), input ));
    }

    @Test
    void testDeletarComIdValido( )
    {
        UUID id = UUID.randomUUID( );
        
        doNothing( ).when(entregaPersistencia).deletar( id );
        
        assertDoesNotThrow( ( ) -> entregaPortaServico.deletar( id.toString( ) ) );
    }

    @Test
    void testDeletarComIdInvalido( )
    {
        String idInvalido = "id_invalido";
        
        assertThrows( EntregaNaoEncontradaException.class, () -> entregaPortaServico.deletar( idInvalido ) );
    }
}