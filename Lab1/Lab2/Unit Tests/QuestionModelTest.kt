import org.junit.Assert.*
import org.junit.Test

class QuestionModelTest {
    private val questionModel = QuestionModel()

    @Test
    fun testNextQuestion() {
        questionModel.setQuestions(listOf(Question("Test question", true)))
        assertNotNull(questionModel.getNextQuestion())
        assertNull(questionModel.getNextQuestion())
    }

    @Test
    fun testGetCurrentQuestion() {
        questionModel.setQuestions(listOf(Question("Test question", true)))
        assertEquals("Test question", questionModel.getCurrentQuestion()?.text)
    }

    @Test
    fun testSetQuestions() {
        questionModel.setQuestions(listOf(Question("Test question", true)))
        assertEquals("Test question", questionModel.getCurrentQuestion()?.text)
    }
}