package cherry.technologies.pokemonrest.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.sql.Timestamp
import java.util.*
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class Form(
    id: UUID? = null,
    createdDate: Timestamp? = null,
    lastModifiedDate: Timestamp? = null,
    version: Long? = null,
    val name:String,
    val url:String,
    @ManyToOne
    @JsonIgnore
    var pokemon: Pokemon? = null)
    : Base(id, version, createdDate, lastModifiedDate)