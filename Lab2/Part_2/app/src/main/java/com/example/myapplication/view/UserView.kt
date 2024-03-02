package com.example.myapplication.view

import com.example.myapplication.model.UserID

class UserView(val userID: UserID) {
    fun getUserWelcomeMessage(): String {
        return "Quiz Taker: ${userID.getName()}"
    }
}