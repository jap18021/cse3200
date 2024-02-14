data class Question(val text: String, val answer: Boolean)

class QuestionModel {
    private var questions: List<Question> = listOf()
    private var currentIndex = 0

    fun setQuestions(questions: List<Question>) {
        this.questions = questions
        this.currentIndex = 0
    }

    fun getNextQuestion(): Question? {
        return if (currentIndex < questions.size) {
            questions[currentIndex++]
        } else {
            null
        }
    }

    fun getCurrentQuestion(): Question? {
        return if (currentIndex < questions.size) {
            questions[currentIndex]
        } else {
            null
        }
    }
}