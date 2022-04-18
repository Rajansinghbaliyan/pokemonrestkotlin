package cherry.technologies.pokemonrest.domain

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@MappedSuperclass
open class Base(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    open var id: UUID? = null,

    @Version
    open var version: Long? = null,

    @CreationTimestamp
    @Column(updatable = false)
    open var createdDate: Timestamp? = null,

    @UpdateTimestamp
    open var lastModifiedDate: Timestamp? = null
)