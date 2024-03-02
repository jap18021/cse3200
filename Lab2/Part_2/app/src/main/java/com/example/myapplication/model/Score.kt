package com.example.myapplication.model

class Score {
    companion object {
        private var score = 0.0
        private var totalQuestions = 0
    }

    fun incrementTotalQuestions() {
        totalQuestions++
    }

    fun incrementScore(scoreChange: Double){
        incrementTotalQuestions()
        score += scoreChange
    }

    fun getScore(): Double{
        return score
    }

    fun resetScore() {
        score = 0.0
        totalQuestions = 0
    }
}