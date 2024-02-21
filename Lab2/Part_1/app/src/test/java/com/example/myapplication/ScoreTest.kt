package com.example.myapplication
import com.example.myapplication.model.Difficulty
import com.example.myapplication.model.Score
import com.example.myapplication.controller.ScoreController
import org.junit.Assert.assertEquals
import org.junit.Test

class ScoreTest {

    @Test
    fun testIncrementScore() {
        val scoreController = ScoreController("Josh")
        scoreController.incrementScore(Difficulty.EASY)
        assertEquals(1.0, scoreController.getScore(), 0.01)
        
        scoreController.incrementScore(Difficulty.MEDIUM)
        assertEquals(3.0, scoreController.getScore(), 0.01)

        scoreController.incrementScore(Difficulty.HARD)
        assertEquals(6.0, scoreController.getScore(), 0.01)
    }
}