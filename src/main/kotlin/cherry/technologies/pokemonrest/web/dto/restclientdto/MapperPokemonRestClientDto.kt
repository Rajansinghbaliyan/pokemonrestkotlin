package cherry.technologies.pokemonrest.web.dto.restclientdto

import cherry.technologies.pokemonrest.domain.*
import cherry.technologies.pokemonrest.domain.Ability
import cherry.technologies.pokemonrest.domain.Move
import cherry.technologies.pokemonrest.domain.Stat
import cherry.technologies.pokemonrest.domain.Type

fun PokemonRestClientDto.restClientDtoToPokemon() =
    Pokemon(
        id = id,
        name = name,
        height = height,
        weight = weight,
        baseExperience = baseExperience,
        moves = moves.map {
            Move(name = it.move.name, url = it.move.url)
        }.toSet(),
        types = types.map {
            Type(name = it.type.name)
        }.toSet(),
        stats = stats.map {
            Stat(baseEffort = it.baseState, effort = it.effort, name = it.stat.name)
        }.toSet(),
        forms = forms.map {
            Form(name = it.name, url = it.url)
        }.toSet(),
        abilities = abilities.map {
            Ability(name = it.ability.name, url = it.ability.url)
        }.toSet()

    )

