package com.example.myapplication.view

import com.example.myapplication.controller.ScoreController

class ScoreView(val score: ScoreController) {
    fun getScoreText(): String {
        return "Score: ${score.getScore()}"
    }
}