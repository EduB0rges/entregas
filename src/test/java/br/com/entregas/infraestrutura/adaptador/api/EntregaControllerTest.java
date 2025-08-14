package br.com.entregas.infraestrutura.adaptador.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.entregas.dominio.modelo.EntregaInput;
import br.com.entregas.dominio.modelo.EntregaOutput;
import br.com.entregas.dominio.porta.EntregaPorta;

class EntregaControllerTest 
{
    @Mock
    private EntregaPorta entregaServico;

    @InjectMocks
    private EntregaController entregaController;

    @BeforeEach
    void setUp() 
    {
        MockitoAnnotations.openMocks( this );
    }

    @Test
    void testBuscarPorId( ) 
    {
        EntregaOutput output = mock( EntregaOutput.class );
        
        when( entregaServico.buscarPorId("1") ).thenReturn( output );

        ResponseEntity<EntregaOutput> response = entregaController.buscarPorId( "1" );
        
        assertEquals( HttpStatus.OK, response.getStatusCode( ) );
        assertEquals( output, response.getBody( ) );
    }

    @Test
    void testCadastrar() 
    {
        EntregaInput  input  = mock( EntregaInput.class );
        EntregaOutput output = mock( EntregaOutput.class );
        
        when( entregaServico.cadastrar( input ) ).thenReturn( output );

        ResponseEntity<EntregaOutput> response = entregaController.cadastrar( input );
        
        assertEquals( HttpStatus.CREATED, response.getStatusCode( ) );
        assertEquals( output, response.getBody( ) );
    }

    @Test
    void testAtualizar() 
    {
        EntregaInput  input  = mock( EntregaInput.class );
        EntregaOutput output = mock( EntregaOutput.class);
        
        when( entregaServico.atualizar( "1", input ) ).thenReturn( output );

        ResponseEntity<EntregaOutput> response = entregaController.atualizar( "1", input );
        
        assertEquals( HttpStatus.OK, response.getStatusCode( ) );
        assertEquals( output, response.getBody( ) );
    }

    @Test
    void testDeletar() 
    {
        doNothing( ).when( entregaServico ).deletar( "1" );
        
        ResponseEntity<Void> response = entregaController.deletar( "1" );
        
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
}

