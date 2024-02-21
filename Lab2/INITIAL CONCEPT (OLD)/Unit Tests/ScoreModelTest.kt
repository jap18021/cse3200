import org.junit.Assert.*
import org.junit.Test

class ScoreModelTest {
    private val scoreModel = ScoreModel()

    @Test
    fun testIncrementScore() {
        scoreModel.incrementScore()
        assertEquals(1, scoreModel.getScore())
    }

    @Test
    fun testGetScore() {
        assertEquals(0, scoreModel.getScore())
        scoreModel.incrementScore()
        assertEquals(1, scoreModel.getScore())
    }

    @Test
    fun testResetScore() {
        scoreModel.incrementScore()
        scoreModel.resetScore()
        assertEquals(0, scoreModel.getScore())
    }
}