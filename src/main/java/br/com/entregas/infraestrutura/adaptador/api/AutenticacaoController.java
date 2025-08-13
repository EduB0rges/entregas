package br.com.entregas.infraestrutura.adaptador.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.entregas.infraestrutura.seguranca.JwtUtil;

@RestController
@RequestMapping("/auth/login")
public class AutenticacaoController 
{
    private static final String USERNAME = "testuser";
    private static final String PASSWORD = "testpass";

    private final JwtUtil jwtUtil;

    public AutenticacaoController( JwtUtil jwtUtil ) 
    {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login( @RequestBody AuthRequest request ) 
    {
        if( USERNAME.equals( request.getUsername( ) ) && PASSWORD.equals( request.getPassword( ) ) ) 
        {
            String token = jwtUtil.generateToken( request.getUsername( ) );
            
            return ResponseEntity.ok( new AuthResponse( token ) );
        }
        return ResponseEntity.status( 401 ).body( "Credenciais inválidas" );
    }
}

class AuthRequest 
{
    private String username;
    private String password;
    
    public String getUsername( ) { return username; }
    public void setUsername( String username ) { this.username = username; }
    public String getPassword( ) { return this.password; }
}

class AuthResponse 
{
    private String token;
    public AuthResponse( String token ) { this.token = token; }
    public String getToken( ) { return token; }
}
