package nl.tijsgroenendaal.iwls_populator.model

import javax.persistence.*

@Entity(name = "pp_cluster")
data class Cluster(
        @Id
        val memberId: Long,
        @OneToMany
        @JoinColumn(name = "cluster_member_id")
        val accounts: List<Account> = listOf()
) : Structure {
        override fun toString(): String {
                return "memberId: $memberId"
        }
}
