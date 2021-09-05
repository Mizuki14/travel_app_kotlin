package op.hachm1.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import android.media.Image
import androidx.annotation.MainThread
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    //private  lateinit var splashImage: Image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //handler = handler()
        //handler.postDelayed({
        //val intent = Intent(this,MainActivity::class.java)
        //startActivity(intent)
        //finish()
        //}
        //}, 3000)

    }
}