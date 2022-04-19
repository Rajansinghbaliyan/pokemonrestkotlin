package cherry.technologies.pokemonrest.web.dto.restclientdto

import com.fasterxml.jackson.annotation.JsonProperty

data class StatsDto(
    @JsonProperty("base_state")
    val baseState:Int,
    val effort: Int,
    val stat: Stat
    )

class Stat(val name:String)