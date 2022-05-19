package com.incubator4.data

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@ExperimentalSerializationApi
@Serializable
data class ResponseResult(
    val header: ResultHeader,
    val data: ResultData
) {
    fun prettyPrint(): String {
        return "相似度: ${header.similarity} \n" +
                data.prettyPrint()
    }
}
