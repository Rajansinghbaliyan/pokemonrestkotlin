package cherry.technologies.pokemonrest.web.services

import cherry.technologies.pokemonrest.domain.Pokemon
import cherry.technologies.pokemonrest.web.GetRestTemplate
import cherry.technologies.pokemonrest.web.customexception.NotFoundException
import cherry.technologies.pokemonrest.web.dto.PokemonDto
import cherry.technologies.pokemonrest.web.dto.dtoToPokemon
import cherry.technologies.pokemonrest.web.repositories.PokemonRepositories
import cherry.technologies.pokemonrest.web.utils.getPokemon
import cherry.technologies.pokemonrest.web.utils.logInfo
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
            log.info("Fetching form the API")
            val url = BASE_URL + id
            val pokemonDto = getPokemon(restTemplate, url).body ?: throw NotFoundException("error in fetching")
            saveToDb(pokemonDto.dtoToPokemon())
        }

        log.info("Pokemon GET: ${result.name}")
        return result
    }

    fun saveToDb(pokemon: Pokemon) = pokemonRepositories.save(pokemon).logInfo(log,"Saved the Pokemon: ${pokemon.name}")
}