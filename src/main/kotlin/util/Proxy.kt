package com.incubator4.util

import io.ktor.client.features.*
import java.net.Proxy

/**
 * 根据输入值返回代理类型
 *
 * @param value 输入的字符串
 * @return 代理的类型
 * @throws IllegalArgumentException 数值非法时抛出
 * @see Proxy.Type
 */
@Throws(IllegalArgumentException::class)
fun getProxyType(value: String): Proxy.Type {
    return when (value) {
        "DIRECT" -> Proxy.Type.DIRECT
        "HTTP" -> Proxy.Type.HTTP
        "SOCKS" -> Proxy.Type.SOCKS
        else -> throw IllegalArgumentException(value)
    }
}

fun Long.toTimeoutMillis(): Long? {
    return if (this < 0L) null
    else if (this == 0L) HttpTimeout.INFINITE_TIMEOUT_MS
    else this
}