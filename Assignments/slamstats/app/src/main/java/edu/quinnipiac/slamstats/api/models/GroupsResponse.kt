package edu.quinnipiac.slamstats.api.models

data class GroupsResponse(
    val get: String,
    val parameters: GroupsParameters,
    val errors: List<Any>,
    val results: Int,
    val response: List<String>
)

data class GroupsParameters(
    val league: String,
    val season: String
)