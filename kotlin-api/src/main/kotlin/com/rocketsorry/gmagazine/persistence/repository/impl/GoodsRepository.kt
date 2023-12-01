package com.rocketsorry.gmagazine.persistence.repository.impl

import com.rocketsorry.gmagazine.persistence.ESQueryBuilder
import com.rocketsorry.gmagazine.persistence.doc.GoodsDoc
import com.rocketsorry.gmagazine.persistence.enum.IdField
import com.rocketsorry.gmagazine.persistence.repository.CommonESQueryRepository
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.stereotype.Repository

@Repository
class GoodsRepository(
    override var operations: ElasticsearchOperations,
    override var esQueryBuilder: ESQueryBuilder

) : CommonESQueryRepository<GoodsDoc> {
    override fun indexName() = "goods"

    override fun docClassType() = GoodsDoc::class.java

    override fun idFieldType() = IdField.GOODS

}