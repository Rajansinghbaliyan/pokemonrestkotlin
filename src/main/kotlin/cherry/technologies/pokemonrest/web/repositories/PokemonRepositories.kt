package cherry.technologies.pokemonrest.web.repositories

import cherry.technologies.pokemonrest.domain.Pokemon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PokemonRepositories: JpaRepository<Pokemon,Int>