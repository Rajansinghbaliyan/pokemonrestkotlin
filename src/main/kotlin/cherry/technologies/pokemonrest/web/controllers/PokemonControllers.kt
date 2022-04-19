package cherry.technologies.pokemonrest.web.controllers

import cherry.technologies.pokemonrest.enums.Fields
import cherry.technologies.pokemonrest.web.services.PokemonServices
import cherry.technologies.pokemonrest.web.utils.responseOk
import org.jetbrains.annotations.NotNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pokemon")
class PokemonControllers(private val pokemonServices: PokemonServices) {

    @GetMapping("/{id}")
    fun getSinglePokemon(@PathVariable id: Int) = pokemonServices.getSinglePokemon(id).responseOk()

    @GetMapping("/range")
    fun getPokemonInRange(
        @RequestParam @NotNull start: Int,
        @RequestParam @NotNull end: Int,
    ) = pokemonServices.getFromRange(start, end).responseOk()

    @GetMapping("/range/db")
    fun getFromDbInRange(
        @RequestParam @NotNull start: Int,
        @RequestParam @NotNull limit: Int,
    ) = pokemonServices.getFromDbOnly(start, limit).responseOk()

    @GetMapping("/range/db/sort")
    fun getAllSortedDb(
        @RequestParam @NotNull start: Int,
        @RequestParam @NotNull limit: Int,
        @RequestParam @NotNull direction: Int,
        @RequestParam @NotNull field: Fields
    ) = pokemonServices.getAllSorted(start, limit, direction, field).responseOk()

    @GetMapping("/type/count")
    fun getCountByType() = pokemonServices.getNoOfByType().responseOk()

    @GetMapping("/type/name")
    fun getTypeAndNames() = pokemonServices.getTypeAndNameOfPokemon().responseOk()
}