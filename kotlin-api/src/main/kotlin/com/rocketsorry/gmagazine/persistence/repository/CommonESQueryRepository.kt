package com.rocketsorry.gmagazine.persistence.repository

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery
import co.elastic.clients.elasticsearch._types.query_dsl.Query
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders
import com.rocketsorry.gmagazine.persistence.ESQueryBuilder
import org.springframework.data.elasticsearch.core.SearchHits

interface CommonESQueryRepository<T : Any> : CommonElasticsearchRepository<T> {

    var esQueryBuilder: ESQueryBuilder

    fun findByUserId(
        userId: String
    ): SearchHits<T> {
        val query = esQueryBuilder.term("user_id", userId)

        val boolQuery: BoolQuery = QueryBuilders.bool()
            .filter(query)
            .build()

        return search(Query(boolQuery), 1)
    }

}