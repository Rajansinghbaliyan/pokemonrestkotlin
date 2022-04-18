package cherry.technologies.pokemonrest.web

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class GetRestTemplate(private final val restTemplateBuilder: RestTemplateBuilder) {
    val restTemplate: RestTemplate = restTemplateBuilder.build()
}