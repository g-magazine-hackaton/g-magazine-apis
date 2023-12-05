package com.rocketsorry.gmagazine.persistence

import co.elastic.clients.elasticsearch._types.FieldValue
import co.elastic.clients.elasticsearch._types.query_dsl.*
import co.elastic.clients.json.JsonData
import org.springframework.data.domain.Sort
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

    fun terms(field: String, values: List<String>): Query {
        val fieldValues = values.map {
            FieldValue.Builder().stringValue(it).build()
        }.toList()

        val termsQueryField = TermsQueryField.Builder().value(fieldValues).build()

        return Query(
            TermsQuery.Builder()
                .field(field)
                .terms(termsQueryField)
                .build()
        )
    }

    fun deSort(field: String): Sort {
        return Sort.by(field).descending()
    }

    fun asSort(field: String): Sort {
        return Sort.by(field).ascending()
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