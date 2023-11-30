package com.rocketsorry.gmagazine.persistence

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery
import co.elastic.clients.elasticsearch._types.query_dsl.Query
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery
import co.elastic.clients.json.JsonData
import org.springframework.stereotype.Component

@Component
class ESQueryBuilder {

    fun term(field: String, value: String): Query {
        return Query(
            TermQuery.Builder()
                .field(field)
                .value(value)
                .build()
        )
    }

    fun range(field: String, gteValue: String, lteValue: String): Query {
        return Query(
            RangeQuery.Builder()
                .field(field)
                .gte(JsonData.of(gteValue))
                .lte(JsonData.of(lteValue))
                .build()
        )
    }

    fun query(boolQuery: BoolQuery): Query {
        return Query(boolQuery)
    }
}