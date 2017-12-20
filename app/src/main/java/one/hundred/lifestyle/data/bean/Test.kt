package one.hundred.lifestyle.data.bean

import java.util.*


/**
 * Created by zzy on 2017/9/15.
 */
class Test(var id: Long = 9,
           var name: String = "name",
           var code: String = "code",
           var createTime: Date = Date())


data class AnimeResponse(
        val mal_id: Int, //1
        val link_canonical: String, //https://myanimelist.net/anime/1/Cowboy_Bebop
        val title: String, //Cowboy Bebop
        val title_english: String, //Cowboy Bebop
        val title_japanese: String, //カウボーイビバップ
        val title_synonyms: Any, //null
        val image_url: String, //https://myanimelist.cdn-dena.com/images/anime/4/19644.jpg
        val type: String, //TV
        val source: String, //Original
        val episodes: Int, //26
        val status: String, //Finished Airing
        val airing: Boolean, //false
        val aired_string: String, //Apr 3, 1998 to Apr 24, 1999
        val aired: Aired,
        val duration: String, //24 min. per ep.
        val rating: String, //R - 17+ (violence & profanity)
        val score: Double, //8.81
        val scored_by: Int, //332559
        val rank: Int, //26
        val popularity: Int, //33
        val members: Int, //635638
        val favorites: Int, //36100
        val synopsis: String, //In the year 2071, humanity has colonized several of the planets and moons of the solar system leaving the now uninhabitable surface of planet Earth behind. The Inter Solar System Police attempts to keep peace in the galaxy, aided in part by outlaw bounty hunters, referred to as "Cowboys." The ragtag team aboard the spaceship Bebop are two such individuals. Mellow and carefree Spike Spiegel is balanced by his boisterous, pragmatic partner Jet Black as the pair makes a living chasing bounties and collecting rewards. Thrown off course by the addition of new members that they meet in their travels—Ein, a genetically engineered, highly intelligent Welsh Corgi; femme fatale Faye Valentine, an enigmatic trickster with memory loss; and the strange computer whiz kid Edward Wong—the crew embarks on thrilling adventures that unravel each member&#039;s dark and mysterious past little by little. Well-balanced with high density action and light-hearted comedy, Cowboy Bebop is a space Western classic and an homage to the smooth and improvised music it is named after. [Written by MAL Rewrite]
        val background: String, //When Cowboy Bebop first aired in spring of 1998 on TV Tokyo, only episodes 2, 3, 7-15, and 18 were broadcast, it was concluded with a recap special known as Yose Atsume Blues. This was due to anime censorship having increased following the big controversies over Evangelion, as a result most of the series was pulled from the air due to violent content. Satellite channel WOWOW picked up the series in the fall of that year and aired it in its entirety uncensored. Cowboy Bebop was not a ratings hit in Japan, but sold over 19,000 DVD units in the initial release run, and 81,000 overall. Protagonist Spike Spiegel won Best Male Character, and Megumi Hayashibara won Best Voice Actor for her role as Faye Valentine in the 1999 and 2000 Anime Grand Prix, respectively.Cowboy Bebop&#039;s biggest influence has been in the United States, where it premiered on Adult Swim in 2001 with many reruns since. The show&#039;s heavy Western influence struck a chord with American viewers, where it became a "gateway drug" to anime aimed at adult audiences.More VideosEpisode VideosEpisode 26Episode 25Episode 24Episode 23
        val premiered: String, //Spring 1998
        val broadcast: String, //Saturdays at 01:00 (JST)
        val related: Related,
        val producer: List<Producer>,
        val licensor: List<Licensor>,
        val studio: List<Studio>,
        val genre: List<Genre>,
        val opening_theme: List<String>,
        val ending_theme: List<String>,
        val episode: List<Any>,
        val episode_last_page: Int //1
)

data class Aired(
        val from: String, //1998-04-03
        val to: String //1999-04-24
)

data class Producer(
        val url: String, //https://myanimelist.net/anime/producer/23/Bandai_Visual
        val name: String //Bandai Visual
)

data class Studio(
        val url: String, //https://myanimelist.net/anime/producer/14/Sunrise
        val name: String //Sunrise
)

data class Genre(
        val url: String, //https://myanimelist.net/anime/genre/1/Action
        val name: String //Action
)

data class Related(
        val Adaptation: List<Adaptation>,
        val story: List<SideStory>,
        val Summary: List<Summary>
)

data class Adaptation(
        val mal_id: Int, //173
        val type: String, //manga
        val url: String, //https://myanimelist.net/manga/173/Cowboy_Bebop
        val title: String //Cowboy Bebop
)

data class Summary(
        val mal_id: Int, //4037
        val type: String, //anime
        val url: String, //https://myanimelist.net/anime/4037/Cowboy_Bebop__Yose_Atsume_Blues
        val title: String //Cowboy Bebop: Yose Atsume Blues
)

data class SideStory(
        val mal_id: Int, //5
        val type: String, //anime
        val url: String, //https://myanimelist.net/anime/5/Cowboy_Bebop__Tengoku_no_Tobira
        val title: String //Cowboy Bebop: Tengoku no Tobira
)

data class Licensor(
        val url: String, //https://myanimelist.net/anime/producer/102/Funimation
        val name: String //Funimation
)