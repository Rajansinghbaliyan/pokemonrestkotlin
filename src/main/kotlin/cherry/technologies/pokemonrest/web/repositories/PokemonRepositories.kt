package cherry.technologies.pokemonrest.web.repositories

import cherry.technologies.pokemonrest.domain.Pokemon
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PokemonRepositories: JpaRepository<Pokemon,Int> {
    fun findByIdBetween(start:Int,end:Int): Page<Pokemon>
}