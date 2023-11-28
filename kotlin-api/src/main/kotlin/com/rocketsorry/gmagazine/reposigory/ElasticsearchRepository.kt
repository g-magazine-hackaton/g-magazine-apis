package com.rocketsorry.gmagazine.reposigory

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import org.elasticsearch.client.Request
import org.elasticsearch.client.RestClient
import org.springframework.stereotype.Repository

import org.elasticsearch.client.ResponseException
import java.io.IOException

data class ElasticsearchResponse<T>(
    val hits: HitsWrapper<T>
)

data class HitsWrapper<T>(
    val hits: List<Hit<T>>
)

data class Hit<T>(
    val _source: T
)

@Repository
class ElasticsearchRepository(private val esClient: RestClient) {
    // Gson 객체 생성
    val gson = Gson()

    fun <T> getDocumentByIndex(indexName: String): List<JsonObject> {
        val request: Request = Request("GET", "/$indexName/_search")
        val jsonString: String = command(request)
        val jsonElement: JsonElement = JsonParser.parseString(jsonString)
        val jsonObject: JsonObject = jsonElement.asJsonObject

        // JSON 문자열을 객체로 파싱
        val response = parseJson<ElasticsearchResponse<T>>(jsonString)
        val elements = response.hits.hits.map { (source: T) -> gson.fromJson(source.toString(), JsonParser.parseString(source.toString()).asJsonObject::class.java) }

        return elements
    }

    /**
     * 저장을 여러번 시도하면 용량 이슈로 해당 인덱스를 readonly로 변경한 뒤 오류를 뱉고 서버가 꺼진다.
     * kibana에서 아래 명령으로 readonly 해제하면 다시 저장 가능해진다. -> 해결이 필요하다.
     *
     * PUT {index_name}/_settings
     * {
     *   "index.blocks.read_only_allow_delete": null
     * }
     */
    fun saveDocument(indexName: String, documentId: String?, documentJson: String): String {
        val endpoint: String = if (documentId != null) "/$indexName/_doc/$documentId" else "/$indexName/_doc"
        val request: Request = Request("POST", endpoint)

        return command(request)
    }

    private fun command(request: Request, documentJson: String = ""): String {
        request.setJsonEntity(documentJson)

        return try {
            esClient.performRequest(request).entity.content.reader().readText()
        } catch (e: ResponseException) {
            // Elasticsearch에서 응답 코드가 에러인 경우를 처리합니다.
            println("Elasticsearch Response Error: ${e.response.statusLine}")
            e.response.entity.content.reader().readText()
        } catch (e: IOException) {
            // IO 예외 처리 (예: 네트워크 문제 등)
            println("IO Error: ${e.message}")
            e.toString()
        } catch (e: Exception) {
            println("알 수 없는 에러가 발생했습니다.")
            e.toString()
        }
    }

    private inline fun <reified T> parseJson(jsonString: String): T {
        // TypeToken을 사용하여 제네릭 타입 정보를 제공
        val typeToken = object : TypeToken<T>() {}.type
        return Gson().fromJson(jsonString, typeToken)
    }
}