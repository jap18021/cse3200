package com.example.myapplication.controller

import com.example.myapplication.model.UserID

class UserController(private val userID: UserID) {

    fun getName(): String? {
        return userID.getName()
    }

    fun setName(newName: String?) {
        userID.setName(newName)
    }
}