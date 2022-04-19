package cherry.technologies.pokemonrest.web.dto.restclientdto

data class PokemonRestClientDto(
    val id:Int,
    val name:String,
    val height:Int,
    val weight:Int,
    val baseExperience:Int,
    val forms:List<FormsDto>,
    val types:List<TypesDto>,
    val stats:List<StatsDto>,
    val moves:List<MovesDto>,
    val abilities: List<AbilityDto>
    )

