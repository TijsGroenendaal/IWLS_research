package nl.tijsgroenendaal.iwls_populator

import kotlinx.coroutines.*
import nl.tijsgroenendaal.iwls_populator.model.*
import nl.tijsgroenendaal.iwls_populator.repositories.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.util.*
import kotlin.random.Random

@RestController
class PopulatorController(
        private val accountRepository: AccountRepository,
        private val clusterRepository: ClusterRepository,
        private val dataDuplicationRepository: DataDuplicationRepository,
        private val compositeKeyRepository: CompositeKeyRepository,
        private val singleKeyRepository: SingleKeyRepository,
        private val dataDuplicationExtraRepository: DataDuplicationExtraRepository
) {

    @PostMapping("data-duplication/single-key")
    suspend fun populateAccountDataDuplicationSingleKey(@RequestParam clusters: Int, @RequestParam accounts: Int, @RequestParam properties: Int, @RequestParam chunks: Int) {

        val first = LocalDate.of(2022, 1, 1)
        for (clus in 1 until clusters + 1) {
            val cluster = clusterRepository.save(Cluster(clus.toLong()))

            coroutineScope {
                (0).until(accounts).chunked(10).forEach { accChunk ->
                    accChunk.forEach {
                        launch {
                            val account = accountRepository.save(Account(memberId = (clus * 100000).toLong() + it, cluster = cluster))
                            val collection = LinkedList<DataDuplication>()
                            for (chunk in 0 until chunks) {
                                val chunkStart = first.plusDays((chunk * 7).toLong())
                                val chunkEnd = first.plusDays(((chunk + 1) * 7).toLong())
                                for (prop in 0 until properties) {
                                    collection.add(DataDuplication(UUID.randomUUID(), chunkStart, chunkEnd, "property$prop", Random.nextFloat(), account))
                                }
                            }

                            dataDuplicationRepository.saveAllAndFlush(collection)
                        }
                    }
                }
            }
        }
    }

    @PostMapping("data-duplication/extra")
    suspend fun populateDataDuplicationExtra(@RequestParam clusters: Int, @RequestParam accounts: Int, @RequestParam properties: Int, @RequestParam chunks: Int) {
        val first = LocalDate.of(2022, 1, 1)
        for (clus in 1 until clusters + 1) {
            coroutineScope {
                (0).until(accounts).chunked(10).forEach { accChunk ->
                    accChunk.forEach {
                        launch {
                            val collection = LinkedList<DataDuplicationExtra>()
                            for (chunk in 0 until chunks) {
                                val chunkStart = first.plusDays((chunk * 7).toLong())
                                val chunkEnd = first.plusDays(((chunk + 1) * 7).toLong())
                                for (prop in 0 until properties) {
                                    collection.add(DataDuplicationExtra(UUID.randomUUID(),  (clus * 100000).toLong() + it, clus.toLong(),  chunkStart, chunkEnd, "property$prop", Random.nextFloat()))
                                }
                            }

                            dataDuplicationExtraRepository.saveAllAndFlush(collection)
                        }
                    }
                }
            }
        }
    }

    @PostMapping("relation/single-key")
    suspend fun populateAccountRelationSingleKey(@RequestParam clusters: Int, @RequestParam accounts: Int, @RequestParam properties: Int, @RequestParam chunks: Int) {
        val first = LocalDate.of(2022, 1, 1)
        for (clus in 1 until clusters + 1) {
            val cluster = clusterRepository.save(Cluster(clus.toLong()))

            coroutineScope {
                (0).until(accounts).chunked(10).forEach { accChunk ->
                    accChunk.forEach {
                        launch {
                            val account = accountRepository.save(Account(memberId = (clus * 100000).toLong() + it, cluster = cluster))

                            val collection = LinkedList<SingleKeyParent>()
                            for (chunk in 0 until chunks) {
                                val chunkStart = first.plusDays((chunk * 7).toLong())
                                val chunkEnd = first.plusDays(((chunk + 1) * 7).toLong())
                                val parent = SingleKeyParent(UUID.randomUUID(), chunkStart, chunkEnd, listOf(), account)
                                val propertyEntities = ((0) until properties).map { SingleKeyChild(UUID.randomUUID(), "property$it", Random.nextFloat(), parent) }
                                parent.propertyPercentages = propertyEntities
                                collection.add(parent)
                            }

                            singleKeyRepository.saveAll(collection)
                        }
                    }
                }
            }
        }
    }

    @PostMapping("relation/composite-key")
    suspend fun populateAccountRelationCompositeKey(@RequestParam clusters: Int, @RequestParam accounts: Int, @RequestParam properties: Int, @RequestParam chunks: Int) {
        val first = LocalDate.of(2022, 1, 1)
        for (clus in 1 until clusters + 1) {
            val cluster = clusterRepository.save(Cluster(clus.toLong()))

            coroutineScope {
                (0).until(accounts).chunked(10).forEach { accChunk ->
                    accChunk.forEach {
                        launch {
                            val account = accountRepository.save(Account(memberId = (clus * 100000).toLong() + it, cluster = cluster))

                            val collection = LinkedList<CompositeKeyParent>()
                            for (chunk in 0 until chunks) {
                                val chunkStart = first.plusDays((chunk * 7).toLong())
                                val chunkEnd = first.plusDays(((chunk + 1) * 7).toLong())
                                val parent = CompositeKeyParent(CompositeKey(account, chunkStart, chunkEnd), UUID.randomUUID(), listOf())
                                val propertyEntities = (0 until properties).map { CompositeKeyChild(UUID.randomUUID(), "property$it", Random.nextFloat(), parent) }
                                parent.propertyPercentages = propertyEntities
                                collection.add(parent)
                            }

                            compositeKeyRepository.saveAll(collection)
                        }
                    }
                }
            }
        }
    }
}