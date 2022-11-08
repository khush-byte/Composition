package com.example.composition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//The entity with list of levels
@Parcelize
enum class Level : Parcelable {
    TEST, EASY, NORMAL, HARD
}