package cherry.technologies.pokemonrest.web.services

import cherry.technologies.pokemonrest.domain.Pokemon
import cherry.technologies.pokemonrest.enums.Fields
import cherry.technologies.pokemonrest.web.GetRestTemplate
import cherry.technologies.pokemonrest.web.customexception.BadRequestException
import cherry.technologies.pokemonrest.web.customexception.NotFoundException
import cherry.technologies.pokemonrest.web.dto.dtoToPokemon
import cherry.technologies.pokemonrest.web.repositories.PokemonRepositories
import cherry.technologies.pokemonrest.web.utils.OffsetBasedPageRequest
import cherry.technologies.pokemonrest.web.utils.getPokemon
import cherry.technologies.pokemonrest.web.utils.logInfo
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.logging.Logger

const val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"

@Service
class PokemonServices(
    private val pokemonRepositories: PokemonRepositories,
    getRestTemplate: GetRestTemplate
) {
    private val restTemplate = getRestTemplate.restTemplate
    private val log: Logger = Logger.getLogger(PokemonServices::class.toString())

    fun getSinglePokemon(id: Int): Pokemon {
        val result = pokemonRepositories.findByIdOrNull(id) ?: run {
            val url = BASE_URL + id
            val pokemonDto = getPokemon(restTemplate, url).body.logInfo(log, "Fetching from API PokemonId: $id")
                ?: throw NotFoundException("error in fetching")
            saveToDb(pokemonDto.dtoToPokemon())
        }
        return result.logInfo(log, "GET Pokemon:${result.name}")
    }


    private fun saveToDb(pokemon: Pokemon) = with(pokemon) {
        moves.forEach { it.pokemon = this }
        abilities.forEach { it.pokemon = this }
        types.forEach { it.pokemon = this }
        stats.forEach { it.pokemon = this }
        forms.forEach { it.pokemon = this }
        pokemonRepositories.save(pokemon).logInfo(log, "Saved the Pokemon: ${pokemon.name}")
    }

    fun getFromRange(start: Int, end: Int) = when {
        start < 0 -> throw BadRequestException("start can't be less than 0.")
        start > end -> throw BadRequestException("start should be greater than end.")
        start == end -> throw BadRequestException("start and end are equal.")
        else -> (start..end)
            .toList()
            .parallelStream()
            .map {
                getSinglePokemon(it)
            }
    }

    fun getFromDbOnly(start: Int, limit: Int) =
        pokemonRepositories.findAll(OffsetBasedPageRequest(limit, start))
            .logInfo(log, "Getting Pokemon form db")

    fun getAllSorted(start: Int, limit: Int, sort: Int, field: Fields) =
        pokemonRepositories.findAll(
            OffsetBasedPageRequest(
                limit,
                start,
                Sort.by(
                    when (sort) {
                        1 -> Sort.Direction.ASC
                        -1 -> Sort.Direction.DESC
                        else -> throw NotFoundException("sort field can have to value 1:ASC -1:DESC")
                    },
                    when (field){
                        Fields.HEIGHT -> field.value
                        Fields.WEIGHT -> field.value
                        Fields.BASE_EXPERIENCE -> field.value
                    }
                )
            )
        )


}

