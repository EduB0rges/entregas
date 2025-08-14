package br.com.entregas.infraestrutura.adaptador.persistencia;

import br.com.entregas.dominio.modelo.EntregaInput;
import br.com.entregas.dominio.modelo.EntregaOutput;
import br.com.entregas.infraestrutura.adaptador.persistencia.entidade.EntregaEntidade;
import br.com.entregas.infraestrutura.adaptador.persistencia.mapper.EntregaMapper;
import br.com.entregas.infraestrutura.adaptador.persistencia.repositorio.EntregaJpaRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EntregaRepositorioAdaptadorTest 
{
    @Mock
    private EntregaJpaRepositorio entregaRepository;

    @Mock
    private EntregaMapper entregaMapper;

    @InjectMocks
    private EntregaRepositorioAdaptador entregaRepositorioAdaptador;

    @BeforeEach
    void setUp( ) 
    {
        MockitoAnnotations.openMocks( this );
    }

    @Test
    void testBuscarPorId( ) 
    {
        UUID            id       = UUID.randomUUID( );
        EntregaEntidade entidade = mock( EntregaEntidade.class );
        
        EntregaOutput output = mock( EntregaOutput.class );
        
        when( entregaRepository.findById( id ) ).thenReturn( Optional.of( entidade ) );
        when( entregaMapper.paraOutput( entidade ) ).thenReturn( output );

        Optional<EntregaOutput> result = entregaRepositorioAdaptador.buscarPorId( id );
        
        assertTrue( result.isPresent( ) );
        assertEquals( output, result.get( ) );
    }

    @Test
    void testBuscarPorIdNaoEncontrado( ) 
    {
        UUID id = UUID.randomUUID( );
        
        when( entregaRepository.findById( id ) ).thenReturn( Optional.empty( ) );

        Optional<EntregaOutput> result = entregaRepositorioAdaptador.buscarPorId( id );
        
        assertFalse( result.isPresent( ) );
    }

    @Test
    void testSalvar( ) 
    {
        EntregaInput    input         = mock( EntregaInput.class );
        EntregaEntidade entidade      = mock( EntregaEntidade.class );
        EntregaEntidade entidadeSalva = mock( EntregaEntidade.class );
        EntregaOutput   output        = mock( EntregaOutput.class   );
        
        when( entregaMapper.paraEntidade( input ) ).thenReturn( entidade );
        when( entregaRepository.save( entidade ) ).thenReturn( entidadeSalva );
        when( entregaMapper.paraOutput( entidadeSalva ) ).thenReturn( output );

        EntregaOutput result = entregaRepositorioAdaptador.salvar( input );
        assertEquals( output, result );
    }

    @Test
    void testDeletarExistente( ) 
    {
        UUID id = UUID.randomUUID( );
        
        when( entregaRepository.existsById( id ) ).thenReturn( true );
        doNothing( ).when( entregaRepository ).deleteById( id );

        entregaRepositorioAdaptador.deletar( id );
        
        verify( entregaRepository, times( 1 ) ).deleteById( id );
    }

    @Test
    void testDeletarNaoExistente( ) 
    {
        UUID id = UUID.randomUUID( );
        
        when( entregaRepository.existsById( id ) ).thenReturn( false );

        entregaRepositorioAdaptador.deletar( id );
        
        verify( entregaRepository, never( ) ).deleteById( id );
    }

    @Test
    void testExistePorId( ) 
    {
        UUID id = UUID.randomUUID( );
        
        when( entregaRepository.existsById( id ) ).thenReturn( true );

        boolean exists = entregaRepositorioAdaptador.existePorId( id );
        
        assertTrue( exists );
    }

    @Test
    void testNaoExistePorId( ) 
    {
        UUID id = UUID.randomUUID();
        when( entregaRepository.existsById( id ) ).thenReturn( false );

        boolean exists = entregaRepositorioAdaptador.existePorId( id );
        
        assertFalse( exists );
    }
}
