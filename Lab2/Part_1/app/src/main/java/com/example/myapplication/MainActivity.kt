package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.controller.NextQuestion
import com.example.myapplication.controller.ScoreController
import com.example.myapplication.model.AllQuestions
import com.example.myapplication.model.UserID
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Pink40

class MainActivity : ComponentActivity() {
    val userID = UserID()

    override fun onCreate(savedInstanceState: Bundle?) {
        userID.setName("Joshua Pintacasi")
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Text("Quiz Taker: ${userID.getName()}")
                QuizComponent(userID)
            }
        }
    }
}
val cardShape = RoundedCornerShape(size = 32.dp)
@Composable
fun QuizComponent(userID: UserID) {
    val allQuestions = AllQuestions()
    val nextQuestion = NextQuestion()
    val score = ScoreController(userID.getName()!!)

    var questionNumber by remember { mutableStateOf(0) }
    var question by remember { mutableStateOf(allQuestions.getQuestion(questionNumber).questionText) }
    var answer by remember { mutableStateOf(allQuestions.getQuestion(questionNumber).answer) }

    Card(
        modifier = Modifier
            .padding(16.dp)
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
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { if (answer) score.incrementScore(allQuestions.getQuestion(questionNumber).difficulty) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("True")
                }
                Button(
                    onClick = { if (!answer) score.incrementScore(allQuestions.getQuestion(questionNumber).difficulty) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("False")
                }
            }

            Text(
                text = "Score: ${score.getScore()}",
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        Button(
            onClick = {
                questionNumber = nextQuestion.getNextLinearQuestion()
                question = allQuestions.getQuestion(questionNumber).questionText
                answer = allQuestions.getQuestion(questionNumber).answer
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Text("Next Question")
        }
    }
}




