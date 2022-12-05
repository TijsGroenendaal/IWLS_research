package nl.tijsgroenendaal.iwls_populator.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import javax.persistence.Embeddable
import javax.persistence.ManyToOne

@Embeddable
data class CompositeKey(
        @JsonIgnore
        @ManyToOne
        var account: Account,
        var startDate: LocalDate,
        var endDate: LocalDate
): java.io.Serializable {
        override fun toString(): String {
                return "startDate: $startDate" +
                        "endDate: $endDate"
        }
}
