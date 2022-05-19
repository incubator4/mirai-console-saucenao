package com.incubator4.data

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@ExperimentalSerializationApi
@Serializable(with = ResultDataSerializer::class)
sealed class ResultData(
) {
    abstract fun prettyPrint(): String

    @ExperimentalSerializationApi
    @Serializable(with = UrlDataSerializer::class)
    sealed class UrlData() : ResultData() {
        abstract val ext_urls: List<String>

        override fun prettyPrint(): String {
            return ext_urls.toString()
        }

        @ExperimentalSerializationApi
        @Serializable
        data class OtherData(override val ext_urls: List<String>) : UrlData()

        @ExperimentalSerializationApi
        @Serializable
        data class SkebData(
            override val ext_urls: List<String>,
            val path: String,
            val creator: String,
            @SerialName("creator_name")
            val creatorName: String,
            @SerialName("author_name")
            val authorName: String?,
            @SerialName("author_url")
            val authorUrl: String?,
        ) : UrlData()

        @ExperimentalSerializationApi
        @Serializable
        data class deviantartData(
            override val ext_urls: List<String>,
            val title: String,
            @SerialName("da_id")
            val daId: String,
            @SerialName("author_name")
            val authorName: String,
            @SerialName("author_url")
            val authorUrl: String,
        ) : UrlData()

        @ExperimentalSerializationApi
        @Serializable
        data class pixivData(
            override val ext_urls: List<String>,
            val title: String,
            @SerialName("pixiv_id")
            val pixivId: Long,
            @SerialName("member_name")
            val memberName: String,
            @SerialName("member_id")
            val memberId: Long,
        ) : UrlData()

        @ExperimentalSerializationApi
        @Serializable
        data class anidbData(
            override val ext_urls: List<String>,
            val source: String,
            @SerialName("anidb_aid")
            val anidbId: Long,
            @SerialName("mal_id")
            val malId: Long? = null,
            @SerialName("anilist_id")
            val anilistId: Long? = null,
            val part: String,
            val year: String,
            @SerialName("est_time")
            val estTime: String,
        ) : UrlData()

        @ExperimentalSerializationApi
        @Serializable
        data class furaffinityData(
            override val ext_urls: List<String>,
            val title: String,
            @SerialName("fa_id")
            val faId: Long,
            @SerialName("author_name")
            val authorName: String,
            @SerialName("author_url")
            val authorUrl: String,
        ) : UrlData()

        @ExperimentalSerializationApi
        @Serializable
        data class twitterData(
            override val ext_urls: List<String>,
            val created_at: String,
            @SerialName("tweet_id")
            val tweetId: String,
            @SerialName("twitter_user_id")
            val twitterUserId: String,
            @SerialName("twitter_user_handle")
            val twitterUserHandle: String,
        ) : UrlData()

        @ExperimentalSerializationApi
        @Serializable
        data class bcyData(
            override val ext_urls: List<String>,
            val title: String,
            @SerialName("bcy_id")
            val bcyId: Long,
            @SerialName("member_name")
            val memberName: String,
            @SerialName("member_id")
            val memberId: Long,
            @SerialName("member_link_id")
            val memberLinkId: Long,
            @SerialName("bcy_type")
            val bcyType: String,
        ) : UrlData()

        @ExperimentalSerializationApi
        @Serializable
        data class furryNetworkData(
            override val ext_urls: List<String>,
            val title: String,
            @SerialName("fn_id")
            val fnId: Long,
            @SerialName("fn_type")
            val fnType: String,
            @SerialName("author_url")
            val authorUrl: String,
            @SerialName("author_name")
            val authorName: String,
        ) : UrlData()

        @ExperimentalSerializationApi
        @Serializable
        data class pawooData(
            override val ext_urls: List<String>,
            @SerialName("pawoo_id")
            val pawooId: Long,
            @SerialName("pawoo_user_acct")
            val pawooUserAcct: String,
            @SerialName("pawoo_user_username")
            val pawooUserUsername: String,
            @SerialName("pawoo_user_display_name")
            val pawooUserDisplayName: String,
        ) : UrlData()
    }


    @ExperimentalSerializationApi
    @Serializable(with = SourceDataSerializer::class)
    sealed class SourceData(

    ) : ResultData() {
        abstract val source: String
        override fun prettyPrint(): String {
            return "source=${this.source}"
        }
        @ExperimentalSerializationApi
        @Serializable
        data class PartData(override val source: String, val part: String, val type: String) : SourceData()
        @ExperimentalSerializationApi
        @Serializable
        data class CreatorData(
            override val source: String,
            val creator: List<String>,
            val eng_name: String,
            val jp_name: String
        ) : SourceData()
    }
    @ExperimentalSerializationApi
    @Serializable
    data class TitleData(
        val title: String,
        val part: String? = null,
        val date: String? = null
    ): ResultData() {
        override fun prettyPrint(): String {
            return "title=${this.title}"
        }
    }
}