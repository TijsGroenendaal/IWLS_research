package nl.tijsgroenendaal.iwls_populator.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity(name = "pp_dup_single")
@Table(name = "pp_dup_single")
data class DataDuplication(
        @Id
        val id: UUID,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val property: String,
        val percentage: Float,
        @JsonIgnore
        @ManyToOne
        val account: Account
): Structure {
        override fun toString(): String {
                return "id: $id" +
                        "startDate: $startDate" +
                        "endDate: $endDate" +
                        "property: $property" +
                        "percentage: $percentage"
        }
}