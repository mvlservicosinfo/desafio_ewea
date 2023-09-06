package br.com.teste.desafio_eawe.repositories;

import br.com.teste.desafio_eawe.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRepEawe extends JpaRepository<Modelo,Integer> {
}
