package cherry.technologies.pokemonrest.web.utils

import cherry.technologies.pokemonrest.web.dto.restclientdto.PokemonRestClientDto
import org.springframework.http.*
import org.springframework.web.client.RestTemplate


fun getPokemon(restTemplate: RestTemplate,url: String): ResponseEntity<PokemonRestClientDto> {
    val headers = HttpHeaders()
    headers.accept = listOf(MediaType.APPLICATION_JSON)
    val entity = HttpEntity<String>(headers)
    return restTemplate.exchange(url,HttpMethod.GET,entity, PokemonRestClientDto::class.java)
}
