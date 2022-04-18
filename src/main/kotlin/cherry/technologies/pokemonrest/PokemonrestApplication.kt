package cherry.technologies.pokemonrest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class PokemonrestApplication

fun main(args: Array<String>) {
    runApplication<PokemonrestApplication>(*args)
}
