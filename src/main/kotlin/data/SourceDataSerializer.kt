/*
 * Copyright (c) 2022.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>
 */

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
object SourceDataSerializer : KSerializer<ResultData.SourceData> {
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