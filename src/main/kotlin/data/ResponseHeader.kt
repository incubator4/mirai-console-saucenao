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
