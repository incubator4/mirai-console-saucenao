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
