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
    if (response.results.isNullOrEmpty()) {
        sender.sendMessage(ReplyConfig.emptyImageData)
        return null
    }
    return response
}