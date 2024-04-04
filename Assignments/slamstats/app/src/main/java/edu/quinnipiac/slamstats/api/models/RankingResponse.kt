package edu.quinnipiac.slamstats.api.models

data class RankingResponse(
    val get: String,
    val parameters: RankingParameters,
    val errors: List<Any>,
    val results: Int,
    val response: List<List<RankingData>>
)

data class RankingParameters(
    val league: String,
    val season: String
)

data class RankingData(
    val position: Int,
    val stage: String,
    val group: Group,
    val team: Team,
    val league: League,
    val country: Country,
    val games: Games,
    val points: Points,
    val form: String,
    val description: String?
)

data class Group(
    val name: String,
    val points: Any?
)

data class Games(
    val played: Int,
    val win: WinLoss,
    val lose: WinLoss
)

data class WinLoss(
    val total: Int,
    val percentage: String
)

data class Points(
    val `for`: Int,
    val against: Int
)