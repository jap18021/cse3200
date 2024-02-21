package com.example.myapplication
import com.example.myapplication.model.UserID
import org.junit.Assert.assertEquals
import org.junit.Test

class UserIDTest {

    @Test
    fun testGetName0() {
        val userID = UserID()
        userID.setName("Joshua Pintacasi")
        assertEquals("Joshua Pintacasi", userID.getName())
    }

    @Test
    fun testGetName1() {
        val userID = UserID()
        userID.setName("Matthew Ghanie")
        assertEquals("Matthew Ghanie", userID.getName())
    }

    @Test
    fun testGetName2() {
        val userID = UserID()
        userID.setName("David Nam")
        assertEquals("David Nam", userID.getName())
    }

    @Test
    fun testGetName3() {
        val userID = UserID()
        userID.setName("Tom McCarthy")
        assertEquals("Tom McCarthy", userID.getName())
    }

    @Test
    fun testGetName4() {
        val userID = UserID()
        userID.setName("Mohammad Abujaffar")
        assertEquals("Mohammad Abujaffar", userID.getName())
    }

    @Test
    fun testGetName5() {
        val userID = UserID()
        userID.setName("Kevin Hernandez")
        assertEquals("Kevin Hernandez", userID.getName())
    }

    @Test
    fun testGetName6() {
        val userID = UserID()
        userID.setName("Toyi Shimizu")
        assertEquals("Toyi Shimizu", userID.getName())
    }
}