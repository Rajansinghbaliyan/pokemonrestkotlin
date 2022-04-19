package cherry.technologies.pokemonrest.web.services

import cherry.technologies.pokemonrest.domain.Pokemon
import cherry.technologies.pokemonrest.enums.Fields
import cherry.technologies.pokemonrest.web.GetRestTemplate
import cherry.technologies.pokemonrest.web.customexception.BadRequestException
import cherry.technologies.pokemonrest.web.customexception.NotFoundException
import cherry.technologies.pokemonrest.web.dto.restclientdto.restClientDtoToPokemon
import cherry.technologies.pokemonrest.web.dto.restdto.PokemonDto
import cherry.technologies.pokemonrest.web.dto.restdto.pokemonToDto
import cherry.technologies.pokemonrest.web.repositories.PokemonRepositories
import cherry.technologies.pokemonrest.web.services.types.PokemonTypeAndName
import cherry.technologies.pokemonrest.web.utils.OffsetBasedPageRequest
import cherry.technologies.pokemonrest.web.utils.getPokemon
import cherry.technologies.pokemonrest.web.utils.logInfo
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.logging.Logger
import java.util.stream.Collectors.*

const val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"

@Service
class PokemonServices(
    private val pokemonRepositories: PokemonRepositories,
    getRestTemplate: GetRestTemplate
) {
    private val restTemplate = getRestTemplate.restTemplate
    private val log: Logger = Logger.getLogger(PokemonServices::class.toString())

    fun getSinglePokemon(id: Int): PokemonDto {
        val result = pokemonRepositories.findByIdOrNull(id)?.pokemonToDto() ?: run {
            val url = BASE_URL + id
            val pokemonDto = getPokemon(restTemplate, url).body.logInfo(log, "Fetching from API PokemonId: $id")
                ?: throw NotFoundException("error in fetching")
            saveToDb(pokemonDto.restClientDtoToPokemon())
        }
        return result.logInfo(log, "GET Pokemon:${result.name}")
    }


    private fun saveToDb(pokemon: Pokemon) = with(pokemon) {
        moves.forEach { it.pokemon = this }
        abilities.forEach { it.pokemon = this }
        types.forEach { it.pokemon = this }
        stats.forEach { it.pokemon = this }
        forms.forEach { it.pokemon = this }
        pokemonRepositories.save(pokemon)
            .pokemonToDto()
            .logInfo(log, "Saved the Pokemon: ${pokemon.name}")
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
            .map { it.pokemonToDto() }
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
                    when (field) {
                        Fields.HEIGHT -> field.value
                        Fields.WEIGHT -> field.value
                        Fields.BASE_EXPERIENCE -> field.value
                    }
                )
            )
        ).map {
            it.pokemonToDto()
        }.logInfo(log, "Getting the sorted list")

    @Transactional
    fun getNoOfByType() =
        pokemonRepositories.streamAll()
            .flatMap { pokemon ->
                pokemon.pokemonToDto()
                    .types.stream().map {
                        PokemonTypeAndName(it, pokemon.name).logInfo(log, "Mapping Pokemon: ${pokemon.name}")
                    }
            }
            .collect(
                groupingBy(
                    PokemonTypeAndName::type,
                    counting()
                )
            )

    @Transactional
    fun getTypeAndNameOfPokemon() =
        pokemonRepositories.streamAll()
            .flatMap { pokemon ->
                pokemon.pokemonToDto()
                    .types.stream()
                    .map {
                        PokemonTypeAndName(it, pokemon.name)
                            .logInfo(log, "Mapping Pokemon: ${pokemon.name}")
                    }
            }
            .collect(
                groupingBy(
                    PokemonTypeAndName::type,
                    mapping(
                        { pokemonType -> pokemonType.name },
                        toSet<String?>().logInfo(log, "Adding to Set Pokemon")
                    )
                )
            )
}

