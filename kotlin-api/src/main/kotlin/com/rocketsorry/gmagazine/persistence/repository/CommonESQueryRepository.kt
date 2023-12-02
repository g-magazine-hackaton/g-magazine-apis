package com.rocketsorry.gmagazine.persistence.repository


import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery
import co.elastic.clients.elasticsearch._types.query_dsl.Query
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders
import com.rocketsorry.gmagazine.persistence.ESQueryBuilder
import com.rocketsorry.gmagazine.persistence.enum.IdField
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.elasticsearch.core.query.UpdateResponse

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

    fun findAll(): SearchHits<T> {
        val boolQuery: BoolQuery = QueryBuilders.bool().build()
        return search(Query(boolQuery))
    }

    fun findAllById(
        searchId: String,
        idField: IdField
    ): SearchHits<T> {
        val query = esQueryBuilder.term(idField.fieldName, searchId)

        val boolQuery: BoolQuery = QueryBuilders.bool()
            .filter(query)
            .build()

        return search(Query(boolQuery))
    }

    fun findAllWithSort(
        idField: IdField
    ): SearchHits<T> {
        val query = esQueryBuilder.deSort(idField.fieldName)
        return searchWithSort(query)
    }


    fun updateLikeCount(
        docId: String
    ): UpdateResponse {
        val script = "ctx._source.liked_cnt += 1"
        return updateWithScript(docId, script)
    }

    fun updateLikeList(
        docId: String,
        magazinId: String,
    ): UpdateResponse {
        val script = "ctx._source.liked_magazine_ids.add(params.new_magazine_id)"
        val param = mapOf("new_magazine_id" to magazinId)

        return updateWithScript(docId, script, param)
    }
}