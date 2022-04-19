package cherry.technologies.pokemonrest.web.dto.restdto

data class PokemonDto(
    val id: Int?,
    val name: String?,
    val height: Int?,
    val weight: Int?,
    val baseExperience: Int?,
    val abilities: List<String?>,
    val forms: List<String?>,
    val types: List<String?>,
    val stats: List<String?>,
    val moves: List<String?>,
)