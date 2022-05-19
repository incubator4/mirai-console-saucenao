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

package com.incubator4.command

import com.incubator4.MiraiConsoleSaucenao
import com.incubator4.config.CommandConfig
import com.incubator4.config.PluginConfig
import com.incubator4.config.ReplyConfig
import com.incubator4.util.*
import com.incubator4.util.logger
import com.incubator4.data.RequestBody
import kotlinx.serialization.ExperimentalSerializationApi
import net.mamoe.mirai.console.command.*
import net.mamoe.mirai.console.command.ConsoleCommandSender.subject
import net.mamoe.mirai.console.command.descriptor.ExperimentalCommandDescriptors
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.MessageSource.Key.quote
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain

@Suppress("unused")
@OptIn(ExperimentalSerializationApi::class)
object Saucenao :
    CompositeCommand(MiraiConsoleSaucenao, primaryName = "saucenao", secondaryNames = CommandConfig.saocenao) {
    private val help: String

    @ExperimentalCommandDescriptors
    @ConsoleExperimentalApi
    override val prefixOptional: Boolean = true

    /**
     * 初始化时从文本文件读取帮助信息
     */
    init {
        val helpFileName = "help.txt"
        help = javaClass.classLoader.getResource("help.txt")?.readText() ?: throw Exception("没有找到 $helpFileName 文件")
    }

    @SubCommand("图片", "img", "image")
    @Description("识别图片")
    suspend fun UserCommandSender.img(img: Image) {
        if (!lock(ConsoleCommandSender.subject)) {
            logger.info("throttled")
            return
        }
        try {
            logger.debug("saucenao: ${img.queryUrl()}")
            searchImage(this, img.queryUrl())
        } finally {
            unlock(subject)
        }
    }

    @SubCommand("链接", "url", "link")
    @Description("识别链接")
    suspend fun UserCommandSender.handle(url: String) {
        if (!lock(ConsoleCommandSender.subject)) {
            logger.info("throttled")
            return
        }
        try {
            logger.debug("saucenao: $url")
            searchImage(this, url)
        }finally {
            unlock(subject)
        }
    }

//    @SubCommand("手机", "phone")
//    @Description("识别图片 手机专用")
//    suspend fun UserCommandSender.handle(){
//        if (!lock(ConsoleCommandSender.subject)) {
//            logger.info("throttled")
//            return
//        }
//        sendMessage((this as CommandSenderOnMessage<*>).fromEvent.source.quote() + "请发送图片")
//        subject.
//    }

    private suspend fun searchImage(sender: CommandSender, url: String) {

        sender.sendMessage((sender as CommandSenderOnMessage<*>).fromEvent.source.quote() + ReplyConfig.notify)
        val body = RequestBody(apiKey = PluginConfig.apiKey, url = url)
        val response = processRequest(sender, body)
        if (response == null) {
            unlock(subject)
            return
        }
        try {
            val chain = buildMessageChain {
                response.results.map { result ->
                    val message: Message = PlainText(result.prettyPrint() + "\n")
                    +message
                }
            }
            sender.sendMessage(chain)
        } catch (e: Exception) {
            logger.error("saucenao error", e)
            return
        }
    }
}