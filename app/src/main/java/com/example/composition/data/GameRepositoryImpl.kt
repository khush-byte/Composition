package com.example.composition.data

import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.entity.Question
import com.example.composition.domain.repository.GameRepository
import java.lang.Integer.min
import kotlin.math.max
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {
    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    //Game question and answer's option generation process
    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        //Visible part
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        //Various answer options
        var options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        //Add right answer to option's list
        options.add(rightAnswer)
        //Other options to generate + or - up to 6 around the correct answer
        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue - 1, rightAnswer + countOfOptions)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        //Mix items in array
        val tempArray = ArrayList(options)
        for (i in 0..tempArray.size) {
            val randomIndex = Random.nextInt(0, tempArray.size)
            val itemToMove = tempArray[randomIndex]
            tempArray.remove(itemToMove);
            tempArray.add(0, itemToMove);
        }
        return Question(sum, visibleNumber, tempArray)
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(
                    10,
                    3,
                    50,
                    8
                )
            }
            Level.EASY -> {
                GameSettings(
                    10,
                    10,
                    70,
                    60
                )
            }
            Level.NORMAL -> {
                GameSettings(
                    20,
                    20,
                    80,
                    60
                )
            }
            Level.HARD -> {
                GameSettings(
                    30,
                    30,
                    90,
                    60
                )
            }
        }
    }
}