package com.example.myapplication.model

data class Question<T> (
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)