package com.incubator4.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

/**
 * 命令别名的配置
 *
 * @constructor 实例化命令配置
 * @see AutoSavePluginConfig
 */
object CommandConfig : AutoSavePluginConfig("CommandConfig") {

    /**
     * 复合命令的别名
     */
    @ValueDescription("复合命令的别名")
    val saocenao: Array<String> by value(arrayOf("识别"))
}
