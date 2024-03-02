package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.controller.NextQuestion
import com.example.myapplication.controller.ScoreController
import com.example.myapplication.model.AllQuestions
import com.example.myapplication.model.UserID
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val userID = UserID()

    private var questionNumber by mutableStateOf(0)
    private var question by mutableStateOf("")
    private var answer by mutableStateOf(false)
    private var scoreValue by mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            questionNumber = it.getInt("questionNumber")
            question = it.getString("question") ?: ""
            answer = it.getBoolean("answer")
            scoreValue = it.getInt("scoreValue")
        }

        userID.setName("Joshua Pintacasi")

        setContent {
            MyApplicationTheme {
                Column {
                    Text("Quiz Taker: ${userID.getName()}")
                    QuizComponent(userID)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("questionNumber", questionNumber)
        outState.putString("question", question)
        outState.putBoolean("answer", answer)
        outState.putInt("scoreValue", scoreValue)
        super.onSaveInstanceState(outState)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}


@Composable
fun QuizComponent(userID: UserID) {
    val allQuestions = AllQuestions()
    val nextQuestion = NextQuestion()

    var questionNumber by remember { mutableStateOf(0) }
    var question by remember { mutableStateOf(allQuestions.getQuestion(questionNumber).questionText) }
    var answer by remember { mutableStateOf(allQuestions.getQuestion(questionNumber).answer) }

    // Create a new ScoreController instance each time the quiz is retaken
    var score by remember { mutableStateOf(ScoreController(userID.getName()!!)) }

    // Check if all questions have been answered
    val quizEnded = remember { mutableStateOf(false) }

    if (quizEnded.value) {
        ResultScreen(score.getScore(), quizEnded) {
            score.resetScore()
            questionNumber = 0
            question = allQuestions.getQuestion(questionNumber).questionText
            answer = allQuestions.getQuestion(questionNumber).answer
            quizEnded.value = false
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = question,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                if (answer) score.incrementScore(allQuestions.getQuestion(questionNumber).difficulty)
                                questionNumber = nextQuestion.getNextLinearQuestion()
                                if (questionNumber == 0) {
                                    quizEnded.value = true
                                } else {
                                    question = allQuestions.getQuestion(questionNumber).questionText
                                    answer = allQuestions.getQuestion(questionNumber).answer
                                }
                            }
                        ) {
                            Text("True")
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            onClick = {
                                if (!answer) score.incrementScore(allQuestions.getQuestion(questionNumber).difficulty)
                                questionNumber = nextQuestion.getNextLinearQuestion()
                                if (questionNumber == 0) {
                                    quizEnded.value = true
                                } else {
                                    question = allQuestions.getQuestion(questionNumber).questionText
                                    answer = allQuestions.getQuestion(questionNumber).answer
                                }
                            }
                        ) {
                            Text("False")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ResultScreen(score: Double, quizEnded: MutableState<Boolean>, resetQuiz: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Quiz Ended",
                    fontSize = 22.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Your score: $score",
                    fontSize = 22.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(
                    onClick = {
                        resetQuiz()
                        quizEnded.value = false
                    }
                ) {
                    Text("Retake Quiz")
                }
            }
        }
    }
}