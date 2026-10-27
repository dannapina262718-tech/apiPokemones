package com.example.pokemonAPI.repository;

import com.example.pokemonAPI.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    boolean existsByNombreIgnoreCase(String nombre);

}
