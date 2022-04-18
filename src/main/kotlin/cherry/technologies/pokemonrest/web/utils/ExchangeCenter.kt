package cherry.technologies.pokemonrest.web.utils

import cherry.technologies.pokemonrest.web.dto.PokemonDto
import org.springframework.web.client.RestTemplate


fun getPokemon(restTemplate: RestTemplate,url: String) = restTemplate.getForEntity(url,PokemonDto::class.java)
