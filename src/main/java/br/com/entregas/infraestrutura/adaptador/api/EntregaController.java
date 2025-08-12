package br.com.entregas.infraestrutura.adaptador.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.entregas.dominio.modelo.EntregaInput;
import br.com.entregas.dominio.modelo.EntregaOutput;
import br.com.entregas.dominio.porta.EntregaPorta;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/entregas")
public class EntregaController 
{
    private final EntregaPorta entregaServico;
    
    public EntregaController( EntregaPorta entregaServico ) 
    {
        this.entregaServico = entregaServico;
    }
    
    @PostMapping
    public ResponseEntity<EntregaOutput> cadastrar( @Valid @RequestBody EntregaInput entrega ) 
    {
        return ResponseEntity.status( HttpStatus.CREATED )
                .body( entregaServico.cadastrar( entrega ) );
    }
}