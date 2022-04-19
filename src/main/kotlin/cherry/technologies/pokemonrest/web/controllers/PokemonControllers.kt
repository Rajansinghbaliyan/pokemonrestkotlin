package cherry.technologies.pokemonrest.web.controllers

import cherry.technologies.pokemonrest.enums.Fields
import cherry.technologies.pokemonrest.web.services.PokemonServices
import org.jetbrains.annotations.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pokemon")
class PokemonControllers(private val pokemonServices: PokemonServices) {

    @GetMapping("/{id}")
    fun getSinglePokemon(@PathVariable id: Int) =
        ResponseEntity.ok()
            .body(pokemonServices.getSinglePokemon(id))

    @GetMapping("/range")
    fun getPokemonInRange(
        @RequestParam @NotNull start: Int,
        @RequestParam @NotNull end: Int,
    ) = ResponseEntity.ok()
        .body(pokemonServices.getFromRange(start, end))

    @GetMapping("/range/db")
    fun getFromDbInRange(
        @RequestParam @NotNull start: Int,
        @RequestParam @NotNull limit: Int,
    ) = ResponseEntity
        .ok()
        .body(pokemonServices.getFromDbOnly(start, limit))

    @GetMapping("/range/db/sort")
    fun getAllSortedDb(
        @RequestParam @NotNull start: Int,
        @RequestParam @NotNull limit: Int,
        @RequestParam @NotNull direction: Int,
        @RequestParam @NotNull field:Fields
    ) = ResponseEntity
        .ok()
        .body(pokemonServices.getAllSorted(start,limit,direction,field))
}