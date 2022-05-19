package com.incubator4.data

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExperimentalSerializationApi
@Serializable
data class ResultHeader(
    val similarity: Double,
    val thumbnail: String,
    @SerialName("index_id")
    val indexId: Int,
    @SerialName("index_name")
    val indexName: String,
    val dupes: Int,
    val hidden:Int
)
