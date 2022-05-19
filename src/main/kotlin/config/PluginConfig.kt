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

package com.incubator4.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

/**
 * 插件的配置
 *
 * @constructor 实例化插件配置
 * @see AutoSavePluginConfig
 */
object PluginConfig : AutoSavePluginConfig("Config") {

    enum class Mode {
        None,
        Whitelist,
        Blacklist
    }

    @Suppress("unused")
    enum class Size {
        Original,
        Regular,
        Small,
        Thumb,
        Mini
    }

    enum class Type {
        Simple,
        Flash,
        Forward
    }

    /**
     * Bot 所有者账号
     */
    @ValueDescription("Bot所有者账号")
    val master: Long by value()

    /**
     * 是否提示Get和Adv命令已受理
     */
    @ValueDescription("是否提示Get和Adv命令已受理")
    val notify: Boolean by value(true)


    /**
     * 默认的冷却时间(单位: s)
     */
    @ValueDescription("默认的冷却时间(单位：s)")
    var cooldown: Int by value(60)

    /**
     * API Key
     */
    @ValueDescription("API Key")
    var apiKey: String by value("")
}
