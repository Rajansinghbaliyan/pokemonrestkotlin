package cherry.technologies.pokemonrest.web.controllers

import cherry.technologies.pokemonrest.web.services.PokemonServices
import org.jetbrains.annotations.NotNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/pokemon")
class PokemonControllers(private val pokemonServices: PokemonServices) {

    @GetMapping("/{id}")
    fun getSinglePokemon(@PathVariable id:Int) = pokemonServices.getSinglePokemon(id)

    @GetMapping("/range")
    fun getPokemonInRange(
        @RequestParam @NotNull start:Int,
        @RequestParam @NotNull end:Int,
        ) = pokemonServices.getFromRange(start,end)

    @GetMapping("/range/db")
    fun getFromDbInRange(
        @RequestParam @NotNull start:Int,
        @RequestParam @NotNull end:Int,
    ) = pokemonServices.getFromDbOnly(start, end)
}