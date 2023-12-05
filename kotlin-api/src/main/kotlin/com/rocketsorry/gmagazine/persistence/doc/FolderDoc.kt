package com.rocketsorry.gmagazine.persistence.doc

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

data class FolderDoc(
    @Id
    var id: String? = null,

    @Field(name = "folder_id", type = FieldType.Keyword)
    var folderId: String,

    @Field(name = "folder_name", type = FieldType.Keyword)
    var folderName: String,

    @Field(name = "up_dt", type = FieldType.Date, format = [DateFormat.date_hour_minute_second])
    var upDt: String
)
