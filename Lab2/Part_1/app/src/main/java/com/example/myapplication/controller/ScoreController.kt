package com.example.myapplication.controller

import com.example.myapplication.model.Difficulty
import com.example.myapplication.model.Score

class ScoreController(val name: String) {
    private val score = Score()

    fun incrementScore(difficulty: Difficulty) {
        val changeScore = when (difficulty) {
            Difficulty.EASY -> 1.0
            Difficulty.MEDIUM -> 2.0
            else -> 3.0
        }
        score.incrementScore(changeScore)
    }

    fun getScore(): Double {
        return score.getScore()
    }
}