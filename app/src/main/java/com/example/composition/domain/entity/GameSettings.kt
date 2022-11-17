package com.example.composition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//The entity of the settings of the game
@Parcelize
data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOgRightAnswers: Int,
    val gameTimeInSeconds: Int

) : Parcelable