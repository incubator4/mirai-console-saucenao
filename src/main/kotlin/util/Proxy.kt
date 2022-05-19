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