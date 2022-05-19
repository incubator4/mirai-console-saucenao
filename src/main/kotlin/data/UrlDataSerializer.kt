package com.incubator4.data

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = ResultData.UrlData::class)
object UrlDataSerializer: KSerializer<ResultData.UrlData> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("UrlData", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): ResultData.UrlData {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        require(element is JsonObject)
        println(element)
        val serializer = when {
            element.containsKey("da_id") -> ResultData.UrlData.deviantartData.serializer()
            element.containsKey("path") -> ResultData.UrlData.SkebData.serializer()
            element.containsKey("pixiv_id") -> ResultData.UrlData.pixivData.serializer()
            element.containsKey("anidb_aid") -> ResultData.UrlData.anidbData.serializer()
            element.containsKey("fa_id") -> ResultData.UrlData.furaffinityData.serializer()
            element.containsKey("twitter_id") -> ResultData.UrlData.twitterData.serializer()
            element.containsKey("bcy_id") -> ResultData.UrlData.bcyData.serializer()
            element.containsKey("fn_id") -> ResultData.UrlData.furryNetworkData.serializer()
            element.containsKey("pawoo_id") -> ResultData.UrlData.pawooData.serializer()

            else -> ResultData.UrlData.OtherData.serializer()
        }
        return decoder.json.decodeFromJsonElement(serializer, element)
    }

    override fun serialize(encoder: Encoder, value: ResultData.UrlData) {

    }

}