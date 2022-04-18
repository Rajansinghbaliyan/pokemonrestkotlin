package cherry.technologies.pokemonrest.domain
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Pokemon(
    @Id
    val id:Int?,
    val name:String,
    val height:Int,
    val weight:Int,
    @JsonProperty("base_experience")
    val baseExperience:Int,

    @OneToMany(mappedBy = "pokemon", cascade = [CascadeType.ALL])
    @Fetch(FetchMode.JOIN)
    var abilities: Set<Ability>,

    @OneToMany(mappedBy = "pokemon", cascade = [CascadeType.ALL])
    @Fetch(FetchMode.JOIN)
    var forms: Set<Form>,

    @OneToMany(mappedBy = "pokemon", cascade = [CascadeType.ALL])
    @Fetch(FetchMode.JOIN)
    val types: Set<Type>,

    @OneToMany(mappedBy = "pokemon", cascade = [CascadeType.ALL])
    @Fetch(FetchMode.JOIN)
    val stats: Set<Stat>,

    @OneToMany(mappedBy = "pokemon", cascade = [CascadeType.ALL])
    @Fetch(FetchMode.JOIN)
    val moves: Set<Move>
    )