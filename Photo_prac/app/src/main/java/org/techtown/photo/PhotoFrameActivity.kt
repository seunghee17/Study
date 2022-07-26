package org.techtown.photo

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.timer

class PhotoFrameActivity: AppCompatActivity() {
    private var currentPosition = 0
    private val photoList = mutableListOf<Uri>()
    private var timer: Timer? = null
    private val photoImageView: ImageView by lazy{
        findViewById<ImageView>(R.id.photoImageView)

    }
    private val backgroundPhotoImageView: ImageView by lazy{
        findViewById<ImageView>(R.id.backgroundPhotoImageView)
    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photoframe)
        Log.d("PhotoFrame", "onCreate!")

        getPhotoUriFromIntent()
    }
    private fun getPhotoUriFromIntent(){
        val size = intent.getIntExtra("photoListSize", 0)
        for(i in 0..size){
            intent.getStringExtra("photo$i")?.let{
                photoList.add(Uri.parse(it))
            }
        }
    }
    private fun startTimer(){
        timer = timer(period = 5 * 1000){
            runOnUiThread{
                Log.d("PhotoF", "5초가 지나감!")
                val current = currentPosition
                val next = if(photoList.size <= currentPosition+1) 0
                                   else currentPosition + 1
                backgroundPhotoImageView.setImageURI(photoList[current])

                photoImageView.alpha = 0f
                photoImageView.setImageURI(photoList[next])


            }
        }
    }
}