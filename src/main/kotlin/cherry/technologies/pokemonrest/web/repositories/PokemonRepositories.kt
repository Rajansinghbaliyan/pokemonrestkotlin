package cherry.technologies.pokemonrest.web.repositories

import cherry.technologies.pokemonrest.domain.Pokemon
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.stream.Stream

@Repository
interface PokemonRepositories: JpaRepository<Pokemon,Int> {
    override fun findAll(pageable: Pageable): Page<Pokemon>
    @Query("select c from Pokemon c")
    fun streamAll(): Stream<Pokemon>
}