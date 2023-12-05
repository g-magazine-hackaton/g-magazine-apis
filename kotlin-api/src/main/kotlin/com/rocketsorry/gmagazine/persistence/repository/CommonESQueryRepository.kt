package com.rocketsorry.gmagazine.persistence.repository


import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery
import co.elastic.clients.elasticsearch._types.query_dsl.Query
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders
import com.rocketsorry.gmagazine.persistence.ESQueryBuilder
import com.rocketsorry.gmagazine.persistence.enum.IdField
import org.springframework.data.elasticsearch.core.SearchHits

interface CommonESQueryRepository<T : Any> : CommonElasticsearchRepository<T> {

    var esQueryBuilder: ESQueryBuilder

    fun idFieldType(): IdField

    fun findById(
        searchId: String,
    ): SearchHits<T> {
        val query = esQueryBuilder.term(idFieldType().fieldName, searchId)

        val boolQuery: BoolQuery = QueryBuilders.bool()
            .filter(query)
            .build()

        return search(Query(boolQuery), 1)
    }

    fun findByIds(
        searchIds: List<String>
    ): SearchHits<T> {
        val query = esQueryBuilder.terms(idFieldType().fieldName, searchIds)

        val boolQuery: BoolQuery = QueryBuilders.bool()
            .filter(query)
            .build()

        return search(Query(boolQuery))
    }

    fun findByIdsWithSort(
        searchIds: List<String>,
        sortField: IdField
    ): SearchHits<T> {
        val query = esQueryBuilder.terms(idFieldType().fieldName, searchIds)
        val sortQuery = esQueryBuilder.deSort(sortField.fieldName)

        val boolQuery: BoolQuery = QueryBuilders.bool()
            .filter(query)
            .build()

        return searchWithSort(Query(boolQuery), sortQuery, 1000)
    }

    fun findAll(): SearchHits<T> {
        val boolQuery: BoolQuery = QueryBuilders.bool().build()
        return search(Query(boolQuery))
    }

    fun findAllByIdWithSort(
        searchId: String,
        searchField: IdField,
        sortField: IdField,
    ): SearchHits<T> {
        val query = esQueryBuilder.term(searchField.fieldName, searchId)
        val sortQuery = esQueryBuilder.deSort(sortField.fieldName)

        val boolQuery: BoolQuery = QueryBuilders.bool()
            .filter(query)
            .build()

        return searchWithSort(Query(boolQuery), sortQuery, 10000)
    }

    fun findAllWithSort(
        idField: IdField
    ): SearchHits<T> {
        val query = esQueryBuilder.deSort(idField.fieldName)
        return searchWithSort(query)
    }

}