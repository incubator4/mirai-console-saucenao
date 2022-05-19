package com.incubator4.data
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * API返回的数据
 *
 * @property error 错误信息
 * @property data 图片信息列表
 * @constructor 实例化数据
 * @see ResultData
 */
@ExperimentalSerializationApi
@Serializable
data class ResponseBody(
    val header: ResponseHeader,
    val results: List<ResponseResult>
) {
    override fun toString(): String {
        return Json.encodeToString(this)
    }
}
