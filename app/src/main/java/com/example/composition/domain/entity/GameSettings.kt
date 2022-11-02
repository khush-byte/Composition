package com.example.composition.domain.entity

//The entity of the settings of the game
data class GameSettings (
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOgRightAnswers: Int,
    val gameTimeInSeconds: Int
)