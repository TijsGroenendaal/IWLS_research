package nl.tijsgroenendaal.iwls_populator.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity(name = "pp_rel_parent_composite")
data class CompositeKeyParent(
        @EmbeddedId
        val compositeKey: CompositeKey,
        @Column(unique = true)
        val id: UUID,
        @OneToMany(
                fetch = FetchType.LAZY,
                cascade = [CascadeType.ALL],
                mappedBy = "parent"
        )
        var propertyPercentages: Collection<CompositeKeyChild>
) : java.io.Serializable, Structure {
        override fun toString(): String {
                return "id: $id"
        }
}

@Entity(name = "pp_rel_child_composite")
data class CompositeKeyChild(
        @Id
        val id: UUID,
        val property: String,
        val percentage: Float,
        @ManyToOne
        @JsonIgnore
        @JoinColumn(name = "parent_id", referencedColumnName = "id")
        val parent: CompositeKeyParent
) : java.io.Serializable {
        override fun toString(): String {
                return "id: $id" +
                        "property: $property" +
                        "percentage: $percentage"
        }
}
