package com.example.a3200final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import android.graphics.Bitmap
import android.media.AudioAttributes
import android.media.MediaPlayer
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.example.a3200final.databinding.ActivityMainBinding
import com.example.a3200final.Models.MM_JSON
import com.example.a3200final.ViewModels.FinalViewModel
import org.json.JSONObject

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val gson = Gson()
    private val metPublicDomainUrl = "https://collectionapi.metmuseum.org/public/collection/v1/objects/"
    private var imageData : MM_JSON? = null
    private lateinit var volleyQueue: RequestQueue
    private lateinit var mediaPlayer: MediaPlayer

    private fun playMusic() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource("https://stream.epic-classical.com/classical-piano")
            prepareAsync()
            setOnPreparedListener { mp -> mp.start() }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        volleyQueue = Volley.newRequestQueue(this)
        val UrlView: FinalViewModel by viewModels()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playMusic()

        binding.nextImageButton.setOnClickListener {
            val nextIMG = UrlView.nextImage()
            val museumURL = metPublicDomainUrl + nextIMG.toString()
            UrlView.setMetaData(museumURL)

                val requestObject = JsonObjectRequest (
                    Request.Method.GET, UrlView.getMetaData(), JSONObject(),
                    { response: JSONObject ->
                        imageData = gson.fromJson(response.toString(), MM_JSON::class.java)
                        val title = "Title: ${imageData?.title}\n"
                        val dates = "Dates: ${imageData?.objectBeginDate}-${imageData?.objectEndDate}\n"
                        val country = "Country: ${imageData?.country}\n"
                        val objectName = "Object Name: ${imageData?.objectName}\n"
                        val text = title + dates + country + objectName
                        binding.textView.text = text

                        val imageURL = imageData?.primaryImage.toString()
                        if (imageURL.isNotEmpty()) {
                            UrlView.setImage(imageURL)

                            val requestImage = ImageRequest(
                                imageURL,
                                { imageResponse: Bitmap ->
                                    binding.imageView.setImageBitmap(imageResponse)
                                }, 0, 0,
                                ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565,
                                { error -> Log.i("PGB", "Error: ${error}") })

                            volleyQueue.add(requestImage)
                        } else {
                            binding.imageView.setImageResource(R.drawable.missingresource)
                        }
                    }, { error -> Log.i("PGB", "Error: ${error}")})
                volleyQueue.add(requestObject)
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}