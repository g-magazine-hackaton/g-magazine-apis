package com.rocketsorry.gmagazine.reposigory

import com.google.gson.JsonObject

interface IElasticsearchRepository {
    fun <T> getDocumentsByIndex(indexName: String): Any
    fun <T> saveDocument(indexName: String, documentId: String?, query: String): String
    fun <T> updateDocumentByIndex(indexName: String, documentId: String, query: String): JsonObject
    fun deleteDocumentByIndex(indexName: String, documentId: String): JsonObject
}