package com.example.a3200lab6

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

var Index = 0

val urls = arrayOf(
    "https://lofi.stream.laut.fm/lofi",
    "https://azura12.instainternet.com/radio/8030/radio.mp3",
    "https://phonk.stream.laut.fm/phonk",
    "https://hiphop24.stream.laut.fm/hiphop24",
    "https://vm65162.streamerr.co/listen/megazone_bollywood_/radio.mp3"
)

val videoUrls = arrayOf(
    "https://cdn.donmai.us/original/1b/2f/__honkai_and_1_more__1b2f621e4a644980d03c02cb5f8fb8b2.mp4",
    "https://cdn.donmai.us/original/2a/b0/__trailblazer_stelle_and_caelus_honkai_and_1_more__2ab01a7b1fd14dd9da9de1310611329c.mp4",
    "https://cdn.donmai.us/original/53/60/__trailblazer_march_7th_acheron_sparkle_firefly_and_14_more_honkai_and_2_more_drawn_by_jiuyuan_zhu_jiang_juzhen_yinghua_and_qiong_kong_youyuan__5360dffc44d4dc9dfd4ef702b2c581d5.mp4",
    "https://cdn.donmai.us/original/ea/43/__trailblazer_kafka_stelle_caelus_aventurine_and_5_more_honkai_and_1_more__ea430e89b350289136893387caf43f45.mp4"
)


class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var leftButton: Button
    private lateinit var rightButton: Button
    private lateinit var radioButton: Button
    private lateinit var leftVidButton: Button
    private lateinit var rightVidButton: Button
    private lateinit var videoButton: Button
    private lateinit var imageView: ImageView
    private lateinit var stationRecyclerView: RecyclerView
    private lateinit var stationAdapter: StationAdapter

    private lateinit var mediaPlayer: MediaPlayer
    private var radioOn: Boolean = false

    private lateinit var playerView: PlayerView
    private lateinit var player: ExoPlayer

    private var isVideoPlaying: Boolean = false
    private var currentVideoIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        leftButton = findViewById(R.id.leftButton)
        rightButton = findViewById(R.id.rightButton)
        radioButton = findViewById(R.id.radioButton)
        leftVidButton = findViewById(R.id.leftVidButton)
        rightVidButton = findViewById(R.id.rightVidButton)
        videoButton = findViewById(R.id.videoButton)
        imageView = findViewById(R.id.imageView)
        stationRecyclerView = findViewById(R.id.stationRecyclerView)
        playerView = findViewById(R.id.player_view)

        setUpRadio()
        setUpVideoPlayer()

        button.setOnClickListener {
            toggleRadio()
        }

        leftButton.setOnClickListener {
            flipStation(-1)
        }

        rightButton.setOnClickListener {
            flipStation(1)
        }

        leftVidButton.setOnClickListener {
            previousVideo()
        }

        rightVidButton.setOnClickListener {
            nextVideo()
        }

        videoButton.setOnClickListener {
            toggleVideo()
        }

        stationRecyclerView.layoutManager = LinearLayoutManager(this)

        stationAdapter = StationAdapter(urls) { position ->
            flipStation(position - Index)
            toggleRadio()
        }
        stationRecyclerView.adapter = stationAdapter
    }

    private fun setUpRadio() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(urls[Index])
            prepareAsync()
        }

        mediaPlayer.setOnPreparedListener {
            radioOn = false
            updateRadioButtonText()
        }
    }

    private fun setUpVideoPlayer() {
        player = ExoPlayer.Builder(this).build()
        playerView.player = player
        val mediaItem = MediaItem.fromUri(videoUrls[currentVideoIndex])
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    private fun toggleRadio() {
        radioOn = !radioOn
        updateRadioButtonText()

        if (radioOn) {
            mediaPlayer.start()
        } else {
            mediaPlayer.pause()
        }
    }

    private fun toggleVideo() {
        if (isVideoPlaying) {
            player.pause()
        } else {
            player.play()
        }
        isVideoPlaying = !isVideoPlaying
    }

    private fun updateRadioButtonText() {
        button.text = if (radioOn) "Radio Off" else "Radio On"
    }

    private fun flipStation(offset: Int) {
        val newIndex = (Index + offset + urls.size) % urls.size
        mediaPlayer.reset()
        mediaPlayer.setDataSource(urls[newIndex])
        mediaPlayer.prepareAsync()
        radioButton.text = urls[newIndex]

        val imageId = getImageResourceId(urls[newIndex])
        imageView.setImageResource(imageId)

        if (radioOn) {
            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
            }
        }

        Index = newIndex
    }

    private fun getImageResourceId(url: String): Int {
        // Map radio station URLs to image resource IDs
        return when (url) {
            "https://lofi.stream.laut.fm/lofi" -> R.drawable.radio1
            "https://azura12.instainternet.com/radio/8030/radio.mp3" -> R.drawable.radio2
            "https://phonk.stream.laut.fm/phonk" -> R.drawable.radio3
            "https://hiphop24.stream.laut.fm/hiphop24" -> R.drawable.radio4
            "https://vm65162.streamerr.co/listen/megazone_bollywood_/radio.mp3" -> R.drawable.radio5
            else -> R.drawable.other
        }
    }


    private fun nextVideo() {
        currentVideoIndex = (currentVideoIndex + 1) % videoUrls.size
        val mediaItem = MediaItem.fromUri(videoUrls[currentVideoIndex])
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    private fun previousVideo() {
        currentVideoIndex = (currentVideoIndex - 1 + videoUrls.size) % videoUrls.size
        val mediaItem = MediaItem.fromUri(videoUrls[currentVideoIndex])
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.release()
    }
}