package com.example.myapplication
import com.example.myapplication.controller.NextQuestion
import com.example.myapplication.model.AllQuestions
import com.example.myapplication.model.Difficulty
import com.example.myapplication.model.Question
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class QuestionTest {

    @Test
    fun testAllQuestions() {
        val allQuestions = AllQuestions()
        Assert.assertEquals(4, allQuestions.getNumberOfQuestions())

        val question = allQuestions.getQuestion(2)
        Assert.assertEquals("Otters are cute", question.questionText)
        Assert.assertEquals(true, question.answer)
        Assert.assertEquals(Difficulty.EASY, question.difficulty)
    }

    @Test
    fun testQuestion() {
        val question = Question("Is water the same as dihydrogen monoxide?", true, Difficulty.MEDIUM)
        Assert.assertEquals("Is water the same as dihydrogen monoxide?", question.questionText)
        Assert.assertEquals(true, question.answer)
        Assert.assertEquals(Difficulty.MEDIUM, question.difficulty)
    }

    @Test
    fun testNextQuestion() {
        val nextQuestion = NextQuestion()

        Assert.assertEquals(1, nextQuestion.getNextLinearQuestion())
        Assert.assertEquals(2, nextQuestion.getNextLinearQuestion())
        Assert.assertEquals(3, nextQuestion.getNextLinearQuestion())
        Assert.assertEquals(0, nextQuestion.getNextLinearQuestion())

        val rand1 = nextQuestion.getNextRandomQuestionNumber()
        val rand2 = nextQuestion.getNextRandomQuestionNumber()
        val rand3 = nextQuestion.getNextRandomQuestionNumber()
        Assert.assertTrue(rand1 >= 0 && rand1 < 4)
        Assert.assertTrue(rand2 >= 0 && rand2 < 4)
        Assert.assertTrue(rand3 >= 0 && rand3 < 4)
    }
}