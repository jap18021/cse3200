package com.example.myapplication.model

class AllQuestions {
    private val allQuestions = arrayListOf<Question<Boolean>>(
        Question<Boolean>("Putting full faith into Otters will result in a successful grade in CSE", false, Difficulty.MEDIUM),
        Question<Boolean>("9 + 10 = 21", false, Difficulty.EASY),
        Question<Boolean>("Otters are cute", true, Difficulty.EASY),
        Question<Boolean>("The scientific method cannot prove conciousness", true, Difficulty.HARD)
    )

    fun getNumberOfQuestions() : Int {
        return allQuestions.size
    }

    fun getQuestion(i: Int) : Question<Boolean>{
        return allQuestions[i]
    }
}