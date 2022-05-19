package com.incubator4.data

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * 进行POST请求所需的参数
 *
 * @property apiKey API Key
 * @property url 请求的URL
 * @property db （必填）999
 * @property num 请求返回的结果数量
 * @property outputType 返回数据的格式 0是html数据 1是xml数据 2是json数据
 * @constructor 实例化请求参数, 参见: [LoliconAPI](https://api.lolicon.app/#/setu?id=%e8%af%b7%e6%b1%82)
 */
@ExperimentalSerializationApi
@Serializable
data class RequestBody(
    @SerialName("api_key")
    val apiKey : String = "",
    val url: String = "",
    val db: Int = 999,
    @SerialName("numres")
    val num: Int = 10,
    @SerialName("output_type")
    val outputType: Int = 2,
    val testmode: Int = 2
) {
    override fun toString(): String {
        val format = Json { encodeDefaults = true }
        return format.encodeToString(value = this)
    }
    fun toMap(): Map<String,String> {
        return mapOf(
            "api_key" to this.apiKey,
            "url" to this.url,
            "db" to this.db.toString(),
            "numres" to this.num.toString(),
            "output_type" to this.outputType.toString(),
            "testmode" to this.testmode.toString()
        )
    }
}
