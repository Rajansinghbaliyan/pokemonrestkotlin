package cherry.technologies.pokemonrest.web.utils

import cherry.technologies.pokemonrest.web.dto.PokemonDto
import org.springframework.http.*
import org.springframework.web.client.RestTemplate
import java.util.*


fun getPokemon(restTemplate: RestTemplate,url: String): ResponseEntity<PokemonDto> {
    val headers = HttpHeaders()
    headers.accept = listOf(MediaType.APPLICATION_JSON)
    val entity = HttpEntity<String>(headers)
    return restTemplate.exchange(url,HttpMethod.GET,entity,PokemonDto::class.java)
}
