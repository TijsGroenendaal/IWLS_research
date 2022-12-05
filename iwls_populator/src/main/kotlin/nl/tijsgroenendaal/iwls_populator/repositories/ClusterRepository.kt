package nl.tijsgroenendaal.iwls_populator.repositories

import nl.tijsgroenendaal.iwls_populator.model.Cluster
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClusterRepository : JpaRepository<Cluster, UUID> {
}