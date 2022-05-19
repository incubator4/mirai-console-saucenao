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
@Serializer(forClass = ResultData.SourceData::class)
object SourceDataSerializer: KSerializer<ResultData.SourceData> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("SourceData", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): ResultData.SourceData {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        require(element is JsonObject)
        println(element)
        val serializer = when {
            element.containsKey("part") -> ResultData.SourceData.PartData.serializer()
            else -> ResultData.SourceData.CreatorData.serializer()
        }
        return decoder.json.decodeFromJsonElement(serializer, element)
    }

    override fun serialize(encoder: Encoder, value: ResultData.SourceData) {

    }
}