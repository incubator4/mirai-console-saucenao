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

package com.incubator4

import com.incubator4.command.Saucenao
import com.incubator4.config.CommandConfig
import com.incubator4.config.PluginConfig
import com.incubator4.config.ProxyConfig
import com.incubator4.config.ReplyConfig
import com.incubator4.util.getProxyType
import com.incubator4.util.toTimeoutMillis
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.permission.AbstractPermitteeId
import net.mamoe.mirai.console.permission.PermissionService.Companion.cancel
import net.mamoe.mirai.console.permission.PermissionService.Companion.permit
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info
import java.net.InetSocketAddress
import java.net.Proxy

object MiraiConsoleSaucenao : KotlinPlugin(
    JvmPluginDescription(
        id = "com.incubator4.mirai-console-saucenao",
        name = "saucenao",
        version = "1.0-SNAPSHOT",
    ) {
        author("incubator4")
    }
) {

    lateinit var client: HttpClient

    override fun onEnable() {

        PluginConfig.reload()
        CommandConfig.reload()
        ReplyConfig.reload()
        ProxyConfig.reload()

        client = HttpClient {
            engine {
                proxy = if (ProxyConfig.type != "DIRECT") Proxy(
                    getProxyType(ProxyConfig.type),
                    InetSocketAddress(ProxyConfig.hostname, ProxyConfig.port)
                ) else Proxy.NO_PROXY
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = ProxyConfig.requestTimeoutMillis.toTimeoutMillis()
                connectTimeoutMillis = ProxyConfig.connectTimeoutMillis.toTimeoutMillis()
                socketTimeoutMillis = ProxyConfig.socketTimeoutMillis.toTimeoutMillis()
            }
        }

        try {
            AbstractPermitteeId.AnyContact.permit(Saucenao.permission)
        } catch (e: Exception) {
            logger.warning("无法自动授予权限，请自行使用权限管理来授予权限")
        }

        Saucenao.register()

        logger.info { "Plugin loaded" }
    }

    override fun onDisable() {
        // 撤销权限
        try {
            AbstractPermitteeId.AnyContact.cancel(Saucenao.permission, true)
        } catch (e: Exception) {
            logger.warning("无法自动撤销权限，请自行使用权限管理来撤销权限")
        }
        Saucenao.unregister()
        logger.info { "Plugin unloaded" }
    }
}