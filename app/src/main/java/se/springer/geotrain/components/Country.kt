package se.springer.geotrain.components

import java.util.Locale

data class Country(
    val name: List<String>,
    val part: WorldPart,
    val code: String,
    val flagRes: Int
)
