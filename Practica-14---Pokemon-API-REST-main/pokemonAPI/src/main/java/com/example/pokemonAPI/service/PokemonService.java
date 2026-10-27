package com.example.pokemonAPI.service;

import com.example.pokemonAPI.Exception.BusinesRuleException;
import com.example.pokemonAPI.Exception.ResourseNotFoundException;
import com.example.pokemonAPI.model.Pokemon;
import com.example.pokemonAPI.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    //hay que hacer validaciones

    public List<Pokemon> listarTodos() {
        return pokemonRepository.findAll();
    }

    public Pokemon guardar(Pokemon pokemon) {

        if (pokemonRepository.existsByNombreIgnoreCase(pokemon.getNombre())) {
            throw new BusinesRuleException("ya existe un pokemon con el mismo nombre");
        }
        return pokemonRepository.save(pokemon);
    }

    public Pokemon buscarPorId(Long id) {
        return  pokemonRepository.findById(id).orElseThrow(()-> new ResourseNotFoundException("No se encontro el pokemon con el id: "+ id));

    }

    public Pokemon actualizarPokemonCompleto(Long id, Pokemon pokemonActualizado) {
        return pokemonRepository.findById(id).map(pokemon -> {
            pokemon.setNombre(pokemonActualizado.getNombre());
            pokemon.setTipo(pokemonActualizado.getTipo());
            pokemon.setNivel(pokemonActualizado.getNivel());
            pokemon.setAtaque(pokemonActualizado.getAtaque());
            pokemon.setDefensa(pokemonActualizado.getDefensa());
            pokemon.setDescripcion(pokemonActualizado.getDescripcion());
            pokemon.setImagenURL(pokemonActualizado.getImagenURL());
            return pokemonRepository.save(pokemon);
        }).orElse(null);
    }

    // PATCH permite modificar solamente algunos campos del recurso.
    // En lugar de sobrescribir todo a ciegas, revisamos qué valores vienen en la petición (no son null)
    // y solo actualizamos esos.
    public Pokemon actualizarPokemonParcial(Long id, Pokemon pokemonParcial) {
        return pokemonRepository.findById(id).map(pokemon -> {
            if (pokemonParcial.getNombre() != null) pokemon.setNombre(pokemonParcial.getNombre());
            if (pokemonParcial.getTipo() != null) pokemon.setTipo(pokemonParcial.getTipo());
            if (pokemonParcial.getNivel() != null) pokemon.setNivel(pokemonParcial.getNivel());
            if (pokemonParcial.getAtaque() != null) pokemon.setAtaque(pokemonParcial.getAtaque());
            if (pokemonParcial.getDefensa() != null) pokemon.setDefensa(pokemonParcial.getDefensa());
            if (pokemonParcial.getDescripcion() != null) pokemon.setDescripcion(pokemonParcial.getDescripcion());
            if (pokemonParcial.getImagenURL() != null) pokemon.setImagenURL(pokemonParcial.getImagenURL());

            return pokemonRepository.save(pokemon);
        }).orElse(null);
    }

    public void eliminarPokemon(Long id) {
        pokemonRepository.deleteById(id);
    }



}
