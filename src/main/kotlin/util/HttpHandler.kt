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

package com.incubator4.util

import com.incubator4.MiraiConsoleSaucenao
import com.incubator4.config.ReplyConfig
import com.incubator4.data.RequestBody
import com.incubator4.data.ResponseBody
import io.ktor.client.request.*
import io.ktor.util.*
import kotlinx.serialization.ExperimentalSerializationApi
import net.mamoe.mirai.console.command.CommandSender

@OptIn(ExperimentalSerializationApi::class, InternalAPI::class)
suspend fun getSaucenaoAPI(body: RequestBody): ResponseBody {
    return MiraiConsoleSaucenao.client.get("https://saucenao.com/search.php") {
        body.toMap().forEach { (key, value) ->
            parameter(key, value)
        }
    }
}

/**
 * 处理HTTP请求
 *
 * @param sender
 * @param body
 * @return
 */
@OptIn(ExperimentalSerializationApi::class)
suspend fun processRequest(sender: CommandSender, body: RequestBody): ResponseBody? {
    val response: ResponseBody?
    try {
        response = getSaucenaoAPI(body)
    } catch (e: Exception) {
        logger.error(e)
        return null
    }
    logger.info(response.toString())
//    if (response.header.isNotEmpty()) {
//        sender.sendMessage(ReplyConfig.invokeException)
//        logger.warning(response.error)
//        return null
//    }
    if (response.results.isEmpty()) {
        sender.sendMessage(ReplyConfig.emptyImageData)
        return null
    }
    return response
}