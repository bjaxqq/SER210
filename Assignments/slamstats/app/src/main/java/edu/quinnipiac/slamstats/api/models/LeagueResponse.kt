package edu.quinnipiac.slamstats.api.models

data class LeagueResponse(
    val get: String,
    val parameters: List<Any>,
    val errors: List<Any>,
    val results: Int,
    val response: List<League>
)

data class League(
    val id: Int,
    val name: String,
    val type: String,
    val logo: String,
    val country: Country,
    val seasons: List<Season>
)

data class Country(
    val id: Int,
    val name: String,
    val code: String?,
    val flag: String?
)

data class Season(
    val season: Int,
    val start: String,
    val end: String
)
