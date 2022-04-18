package cherry.technologies.pokemonrest.web.controllers

import cherry.technologies.pokemonrest.web.services.PokemonServices
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/pokemon")
class PokemonControllers(val pokemonServices: PokemonServices) {

    @GetMapping("/{id}")
    fun getSinglePokemon(@PathVariable id:Int) = pokemonServices.getSinglePokemon(id)
}