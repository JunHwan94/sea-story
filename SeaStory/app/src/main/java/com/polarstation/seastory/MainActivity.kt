package com.polarstation.seastory

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
//    private lateinit var binding: com.polarstation.seastory.databinding.ActivityMainBinding
    private lateinit var player : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        player = MediaPlayer.create(this, R.raw.sound)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        player.setOnCompletionListener(MediaPlayer::start)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)

        val imageView = findViewById<ImageView>(R.id.imageView)
        setGifImage(imageView)
    }

    private fun setGifImage(imageView: ImageView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Glide.with(this)
                .load(getDrawable(R.drawable.sea))
                .into(imageView)
        }else {
            Glide.with(this)
                .load(R.drawable.sea)
                .into(imageView)
        }
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onResume() {
        super.onResume()
        if(!player.isPlaying)
            player.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button1 -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://sea33.shop")))
            }
            R.id.button2 -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://sea34.shop")))
            }
        }
    }
}
