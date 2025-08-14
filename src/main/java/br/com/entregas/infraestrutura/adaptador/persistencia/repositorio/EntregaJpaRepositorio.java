package br.com.entregas.infraestrutura.adaptador.persistencia.repositorio;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.entregas.infraestrutura.adaptador.persistencia.entidade.EntregaEntidade;

@Repository
public interface EntregaJpaRepositorio extends JpaRepository<EntregaEntidade, UUID> 
{
}