package br.com.entregas.infraestrutura.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.entregas.infraestrutura.seguranca.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig 
{
    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig( JwtAuthenticationFilter jwtAuthFilter )
    {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception 
    {
        return http
                .csrf( ).disable( )
                .authorizeHttpRequests( )
                .requestMatchers( "/auth/login/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/v3/api-docs.yaml").permitAll( )
                .anyRequest( ).authenticated( )
                .and( )
                .sessionManagement( )
                .sessionCreationPolicy( SessionCreationPolicy.STATELESS )
                .and( )
                .addFilterBefore( jwtAuthFilter, UsernamePasswordAuthenticationFilter.class )
                .build( );
    }
}
