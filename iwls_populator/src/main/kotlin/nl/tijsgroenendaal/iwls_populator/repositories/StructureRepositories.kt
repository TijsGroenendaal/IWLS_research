package nl.tijsgroenendaal.iwls_populator.repositories

import nl.tijsgroenendaal.iwls_populator.model.CompositeKeyParent
import nl.tijsgroenendaal.iwls_populator.model.DataDuplication
import nl.tijsgroenendaal.iwls_populator.model.DataDuplicationExtra
import nl.tijsgroenendaal.iwls_populator.model.SingleKeyParent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface DataDuplicationRepository : JpaRepository<DataDuplication, UUID>

@Repository
interface SingleKeyRepository : JpaRepository<SingleKeyParent, UUID>

@Repository
interface CompositeKeyRepository : JpaRepository<CompositeKeyParent, UUID>

@Repository
interface DataDuplicationExtraRepository : JpaRepository<DataDuplicationExtra, UUID>