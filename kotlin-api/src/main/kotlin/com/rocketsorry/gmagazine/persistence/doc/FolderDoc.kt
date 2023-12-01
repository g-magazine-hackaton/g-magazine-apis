package com.rocketsorry.gmagazine.persistence.doc

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDate

data class FolderDoc(
    @Id
    var id: String? = null,

    @Field(name = "folder_id", type = FieldType.Keyword)
    var folderId: String,

    @Field(name = "consumer_id", type = FieldType.Keyword)
    var consumerId: String,

    @Field(name = "folder_name", type = FieldType.Keyword)
    var folderName: String,

    @Field(name = "up_dt", type = FieldType.Date, format = [DateFormat.date])
    var upDt: LocalDate
)
