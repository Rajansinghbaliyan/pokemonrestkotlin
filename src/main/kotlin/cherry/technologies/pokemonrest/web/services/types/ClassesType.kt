package cherry.technologies.pokemonrest.web.services.types

class PokemonTypeAndName(val type: String?, val name: String?)

class PokemonMovesAndName(val name:String?,val types:List<String>, val noOfMoves:Int): Comparable<PokemonMovesAndName>{
//    fun compare(o1: PokemonMovesAndName?, o2: PokemonMovesAndName?) = (o2?.noOfMoves ?: 0) - (o1?.noOfMoves ?: 0)
    override fun compareTo(other: PokemonMovesAndName) = other.noOfMoves - this.noOfMoves

}