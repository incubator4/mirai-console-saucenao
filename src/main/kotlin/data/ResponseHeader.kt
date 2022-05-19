package com.incubator4.data

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExperimentalSerializationApi
@Serializable
data class ResponseHeader(
    @SerialName("user_id")
    val userId: String,
    @SerialName("accountType")
    val account_type: String? = null,
    @SerialName("short_limit")
    val shortLimit: String,
    @SerialName("long_limit")
    val longLimit: String,
    @SerialName("short_remaining")
    val shortRemaining: Int,
    @SerialName("long_remaining")
    val longRemaining: Int,
    val status: Int,
    @SerialName("results_requested")
    val resultsRequested: Int,
    val index: Map<String, Index>? = null
) {

    @ExperimentalSerializationApi
    @Serializable
    data class Index(
        val status: Long,
        @SerialName("parent_id")
        val parentID: Long,
        val id: Long,
        val results: Long
    )
}
