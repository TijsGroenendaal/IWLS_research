package nl.tijsgroenendaal.iwls_populator.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity(name = "pp_account")
data class Account(
        @Id
        val memberId: Long,
        @OneToMany
        @JoinColumn(name = "account_member_id")
        val dataDuplication: List<DataDuplication> = listOf(),
        @OneToMany
        @JoinColumn(name = "account_member_id")
        var compositeKey: List<CompositeKeyParent> = listOf(),
        @OneToMany
        @JoinColumn(name = "account_member_id")
        val singleKey: List<SingleKeyParent> = listOf(),
        @JsonIgnore
        @ManyToOne
        val cluster: Cluster
) {
        override fun toString(): String {
                return "memberId: $memberId"
        }
}
