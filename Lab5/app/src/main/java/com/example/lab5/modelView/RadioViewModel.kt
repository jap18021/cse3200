package com.example.lab5.modelView

import com.example.lab5.model.Radio
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RadioViewModel : ViewModel() {
    private val _currentRadio = MutableStateFlow<Radio?>(null)
    private val _isPlaying = MutableStateFlow(false)
    private val _volume = MutableStateFlow(50)

    val currentRadio: StateFlow<Radio?> = _currentRadio
    val isPlaying: StateFlow<Boolean> = _isPlaying
    val volume: StateFlow<Int> = _volume

    private var mediaPlayer: MediaPlayer? = null

    fun currentRadio(radio: Radio) {
        _currentRadio.value = radio
    }

    fun radioState() {
        if (_isPlaying.value) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            _isPlaying.value = false
        } else {
            val radio = _currentRadio.value ?: return
            mediaPlayer = MediaPlayer().apply {
                setDataSource(radio.radioURL)
                setOnPreparedListener { start () }
                prepareAsync()
            }
            _isPlaying.value = true
        }
    }

    fun setVol(nVol: Int) {
        _volume.value = nVol.coerceIn(0, 100)
        val volume = _volume.value / 100f
        mediaPlayer?.setVolume(volume, volume)
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
    }
}