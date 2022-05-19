package com.incubator4


import com.incubator4.data.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.plugins

@OptIn(ExperimentalSerializationApi::class)
suspend fun main() {

        val client = HttpClient(CIO) {
            engine {
//                proxy = Proxy.NO_PROXY
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
        val body = RequestBody(
            apiKey = "ac779f80412402d0996c871c1d9a4c1c518ab537",
            url = "http://saucenao.com/images/static/banner.gif"
        )

        val response : ResponseBody = client.get("https://saucenao.com/search.php") {
            body.toMap().forEach { (key, value) ->
                parameter(key, value)
            }
        }
        print(response.header)
}