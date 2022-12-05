package nl.tijsgroenendaal.iwls_populator.repositories

import nl.tijsgroenendaal.iwls_populator.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long>