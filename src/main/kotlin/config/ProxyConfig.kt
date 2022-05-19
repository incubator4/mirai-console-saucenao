package com.incubator4.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

/**
 * 网络代理的配置
 *
 * @constructor 实例化网络代理的配置
 * @see AutoSavePluginConfig
 */
object ProxyConfig : AutoSavePluginConfig("ProxyConfig") {

    /**
     * 代理类型
     */
    @ValueDescription("可选：DIRECT/HTTP/SOCKS")
    val type: String by value("DIRECT")

    /**
     * IP地址
     */
    @ValueDescription("IP地址")
    val hostname: String by value("localhost")

    /**
     * 端口
     */
    @ValueDescription("端口")
    val port: Int by value(1080)

    @ValueDescription("整个HTTP请求的超时时间，单位：毫秒")
    val requestTimeoutMillis: Long by value(-1L)

    @ValueDescription("建立连接的超时时间，单位：毫秒")
    val connectTimeoutMillis: Long by value(-1L)

    @ValueDescription("两个数据包之间间隔的超时时间，单位：毫秒")
    val socketTimeoutMillis: Long by value(-1L)
}
