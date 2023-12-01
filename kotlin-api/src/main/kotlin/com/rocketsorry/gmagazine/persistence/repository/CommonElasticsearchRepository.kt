package com.rocketsorry.gmagazine.persistence.repository

import co.elastic.clients.elasticsearch._types.query_dsl.Query
import org.springframework.data.domain.Sort
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.data.elasticsearch.core.query.ByQueryResponse
import org.springframework.data.elasticsearch.core.query.UpdateQuery

interface CommonElasticsearchRepository<T : Any> {
    var operations: ElasticsearchOperations

    fun indexName(): String

    fun docClassType(): Class<T>

    fun indexCoordinates() = IndexCoordinates.of(indexName())


    fun save(doc: T): T = operations.save(doc, indexCoordinates())

    fun search(dslQuery: Query, maxResult: Int = 10000): SearchHits<T> {
        val query = NativeQueryBuilder()
            .withQuery(dslQuery)
            .withMaxResults(maxResult)
            .build()
        return operations.search(query, docClassType(), indexCoordinates())
    }

    fun update(updateQuery: UpdateQuery): ByQueryResponse {
        val query = updateQuery.indexName
        val indexs = indexCoordinates()
        return operations.updateByQuery(updateQuery, indexCoordinates())
    }

    fun searchWithSort(sort: Sort, maxResult: Int = 1000): SearchHits<T> {
        val query = NativeQueryBuilder()
            .withMaxResults(maxResult)
            .withSort(sort)
            .build()

        return operations.search(query, docClassType(), indexCoordinates())
    }
}