package com.example.lab5.viewController

import com.example.lab5.R
import com.example.lab5.model.Radio
import com.example.lab5.modelView.RadioViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RadioController(viewModel: RadioViewModel = viewModel<RadioViewModel>()) {
    val currentRadio = viewModel.currentRadio.collectAsState().value
    val isPlaying = viewModel.isPlaying.collectAsState().value
    val volume = viewModel.volume.collectAsState().value

    val radios = listOf(
        Radio("Station 1", "https://lofi.stream.laut.fm/lofi", R.drawable.radio1),
        Radio("Station 2", "https://azura12.instainternet.com/radio/8030/radio.mp3;", R.drawable.radio2),
        Radio("Station 3", "https://phonk.stream.laut.fm/phonk", R.drawable.radio3),
        Radio("Station 4", "https://hiphop24.stream.laut.fm/hiphop24", R.drawable.radio4),
        Radio("Station 5", "https://vm65162.streamerr.co/listen/megazone_bollywood_/radio.mp3", R.drawable.radio5)
    )

    Column(modifier = Modifier.padding(16.dp)) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            radios.forEach { radio ->
                Column(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Image(painter = painterResource(id = radio.radioIMG), contentDescription = null, modifier = Modifier.fillMaxWidth())
                    Button(onClick = { viewModel.currentRadio(radio); viewModel.radioState() }) {
                        Text(text = if (isPlaying && currentRadio == radio) "Stop" else "Start")
                    }
                }
            }
        }

        Row(
            modifier = Modifier.padding(bottom = 50.dp),
        ) {
            Slider(
                value = volume.toFloat(),
                onValueChange = { newVolume -> viewModel.setVol(newVolume.toInt()) },
                valueRange = 0f..100f
            )
        }
    }
}