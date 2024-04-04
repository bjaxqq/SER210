import edu.quinnipiac.slamstats.api.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIService {
    @Headers(
        "X-RapidAPI-Key: 02371c166emsh9b0e55d0bb15564p163f02jsn25e01321b1bd",
        "X-RapidAPI-Host: api-basketball.p.rapidapi.com"
    )
    @GET("teams")
    fun getTeam(
        @Query("league") league: String,
        @Query("season") season: String
    ): Call<TeamResponse>

    @Headers(
        "X-RapidAPI-Key: 02371c166emsh9b0e55d0bb15564p163f02jsn25e01321b1bd",
        "X-RapidAPI-Host: api-basketball.p.rapidapi.com"
    )
    @GET("leagues")
    fun getLeague(): Call<LeagueResponse>

    @Headers(
        "X-RapidAPI-Key: 02371c166emsh9b0e55d0bb15564p163f02jsn25e01321b1bd",
        "X-RapidAPI-Host: api-basketball.p.rapidapi.com"
    )
    @GET("standings")
    fun getRanking(
        @Query("league") league: String,
        @Query("season") season: String
    ): Call<RankingResponse>

    @Headers(
        "X-RapidAPI-Key: 02371c166emsh9b0e55d0bb15564p163f02jsn25e01321b1bd",
        "X-RapidAPI-Host: api-basketball.p.rapidapi.com"
    )
    @GET("standings/groups")
    fun getGroups(
        @Query("season") season: String,
        @Query("league") league: String
    ): Call<GroupsResponse>
}
