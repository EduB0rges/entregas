package br.com.entregas.infraestrutura.adaptador.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.entregas.dominio.modelo.EntregaInput;
import br.com.entregas.dominio.modelo.EntregaOutput;
import br.com.entregas.dominio.porta.EntregaPorta;

@RestController
@RequestMapping(value = "/api/entregas")
public class EntregaController 
{
	private static final Logger LOGGER = LoggerFactory.getLogger( EntregaController.class );
	
    private final EntregaPorta entregaServico;
    
    public EntregaController( EntregaPorta entregaServico ) 
    {
        this.entregaServico = entregaServico;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EntregaOutput> buscarPorId( @PathVariable String id ) 
    {
    	LOGGER.debug( "Buscando entrega com ID: {}", id );
    	
        return ResponseEntity.ok( entregaServico.buscarPorId( id ) );
    }
    
    @PostMapping
    public ResponseEntity<EntregaOutput> cadastrar( @RequestBody EntregaInput entrega ) 
    {
    	LOGGER.debug( "Cadastrando nova entrega: {}", entrega );
    	
        return ResponseEntity.status( HttpStatus.CREATED )
                .body( entregaServico.cadastrar( entrega ) );
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EntregaOutput> atualizar( @PathVariable String id, 
                                                    @RequestBody EntregaInput entrega ) 
    {
    	LOGGER.debug( "Atualizando entrega com ID: {}, dados: {}", id, entrega );
    	
        return ResponseEntity.ok( entregaServico.atualizar( id, entrega ) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar( @PathVariable String id ) 
    {
    	LOGGER.debug( "Deletando entrega com ID: {}", id );
    	
        entregaServico.deletar( id );
        
        return ResponseEntity.noContent( ).build( );
    }
}