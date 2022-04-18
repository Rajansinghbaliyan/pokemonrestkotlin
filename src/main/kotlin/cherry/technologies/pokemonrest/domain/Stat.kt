package cherry.technologies.pokemonrest.domain

import com.fasterxml.jackson.annotation.JsonIgnore
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
    @ManyToOne
    @JsonIgnore
    var pokemon: Pokemon? = null)
    : Base(id, version, createdDate, lastModifiedDate)