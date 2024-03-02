package com.example.myapplication.view

import com.example.myapplication.model.AllQuestions

class QuestionView(val questionNumber: Int, val allQuestions: AllQuestions) {
    fun getQuestionText(): String {
        return allQuestions.getQuestion(questionNumber).questionText
    }

    fun getQuestionAnswer(): Boolean {
        return allQuestions.getQuestion(questionNumber).answer
    }
}