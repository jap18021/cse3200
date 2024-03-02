package com.example.myapplication.controller

import com.example.myapplication.model.AllQuestions

class NextQuestion {
    private val totalQuestion = AllQuestions().getNumberOfQuestions()
    companion object{
        private var currentQuestion = 0
    }


    fun getNextLinearQuestion() : Int {
        currentQuestion = (currentQuestion + 1) % totalQuestion
        return currentQuestion
    }

    fun getNextRandomQuestionNumber() : Int {
        val getRand = (0..totalQuestion).random()
        return getRand
    }
}