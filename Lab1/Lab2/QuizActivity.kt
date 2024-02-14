class QuizActivity : AppCompatActivity() {
    private val quizController = QuizController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Initialize quiz with questions
        quizController.startQuiz(listOf(Question("Question 1", true), Question("Question 2", false)))

        // Set user ID
        quizController.setUserId("User1")

        // Set up button listeners
        findViewById<Button>(R.id.button_true).setOnClickListener {
            quizController.answerQuestion(true)
            updateQuestion()
        }

        findViewById<Button>(R.id.button_false).setOnClickListener {
            quizController.answerQuestion(false)
            updateQuestion()
        }

        findViewById<Button>(R.id.button_skip).setOnClickListener {
            quizController.skipQuestion()
            updateQuestion()
        }

        findViewById<Button>(R.id.button_finish_early).setOnClickListener {
            finishQuiz()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val question = quizController.getCurrentQuestion()
        if (question != null) {
            findViewById<TextView>(R.id.text_question).text = question.text
        } else {
            finishQuiz()
        }
    }

    private fun finishQuiz() {
        val score = quizController.getScore()
        // Display score to user
    }
}