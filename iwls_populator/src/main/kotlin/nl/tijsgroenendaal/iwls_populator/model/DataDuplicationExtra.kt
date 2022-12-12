package nl.tijsgroenendaal.iwls_populator.model

import java.time.LocalDate
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "pp_dup_extra")
@Table(name = "pp_dup_extra")
data class DataDuplicationExtra(
        @Id
        val id: UUID,
        val accountMemberId: Long,
        val clusterMemberId: Long,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val property: String,
        val percentage: Float
)