package edu.quinnipiac.slamstats.api.models

data class TeamResponse(
    val get: String,
    val parameters: Parameters,
    val errors: List<Any>,
    val results: Int,
    val response: List<Team>
)

data class Parameters(
    val league: String,
    val season: String
)

data class Team(
    val id: Int,
    val name: String,
    val logo: String,
    val national: Boolean,
    val country: Country
)