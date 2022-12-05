package nl.tijsgroenendaal.iwls_populator.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity(name = "pp_rel_parent_single")
data class SingleKeyParent(
        @Id
        val id: UUID,
        val startDate: LocalDate,
        val endDate: LocalDate,
        @OneToMany(
                fetch = FetchType.LAZY,
                cascade = [CascadeType.ALL],
                mappedBy = "parent"
        )
        var propertyPercentages: Collection<SingleKeyChild>,
        @JsonIgnore
        @ManyToOne
        val account: Account
): Structure {
        override fun toString(): String {
                return "id: $id" +
                        "startDate: $startDate" +
                        "endDate: $endDate"
        }
}

@Entity(name = "pp_rel_child_single")
data class SingleKeyChild(
        @Id
        val id: UUID,
        val property: String,
        val percentage: Float,
        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "parent_id", referencedColumnName = "id")
        val parent: SingleKeyParent
) {
        override fun toString(): String {
                return "id: $id" +
                        "property: $property" +
                        "percentage: $percentage"
        }
}