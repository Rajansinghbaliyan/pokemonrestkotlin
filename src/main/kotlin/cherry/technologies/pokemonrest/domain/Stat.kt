package cherry.technologies.pokemonrest.domain

import java.sql.Timestamp
import java.util.*
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class Stat(
    id: UUID? = null,
    createdDate: Timestamp? = null,
    lastModifiedDate: Timestamp? = null,
    version: Long? = null,
    val baseEffort: Int,
    val effort:Int,
    val name:String,
    @ManyToOne var pokemon: Pokemon? = null)
    : Base(id, version, createdDate, lastModifiedDate)