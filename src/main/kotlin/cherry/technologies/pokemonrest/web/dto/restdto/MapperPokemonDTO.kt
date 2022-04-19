package cherry.technologies.pokemonrest.web.dto.restdto

import cherry.technologies.pokemonrest.domain.Pokemon

fun Pokemon.pokemonToDto() =
    with(this){
        PokemonDto(
            id = id,
            name = name,
            height = height,
            weight = weight,
            baseExperience = baseExperience,
            abilities = abilities.map {
                it.name
            },
            types = types.map {
                it.name
            },
            moves = moves.map {
                it.name
            },
            forms = forms.map {
                it.name
            },
            stats = stats.map {
                it.name
            }
        )
    }