package cherry.technologies.pokemonrest.web

import cherry.technologies.pokemonrest.config.RestResponseErrorConfig
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class GetRestTemplate(private final val restTemplateBuilder: RestTemplateBuilder) {
    val restTemplate: RestTemplate = restTemplateBuilder.errorHandler(RestResponseErrorConfig()).build()
}