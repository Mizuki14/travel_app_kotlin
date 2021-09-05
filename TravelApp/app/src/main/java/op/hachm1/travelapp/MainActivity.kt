package op.hachm1.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    //private val SPLASH_TIME_OUT = 3000L
    private lateinit var continentSpinner: Spinner
    private lateinit var startButton: Button
    //private lateinit var splashImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //populate continent in the spinner
        continentSpinner = findViewById(R.id.continentSpinner)
        populateContinents(continentSpinner, resources.getStringArray(R.array.continents))

        //Splash screen to main
        //splashImage = findViewById(R.id.splashImage)

        //setContentView(R.layout.activity_main)
        //Timer(Intent(this@MainActivity, SplashActivity::class.java, false)
        //))
        startButton = findViewById(R.id.startButton)
        startButton.setOnClickListener{
            //val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            //splashImage.startAnimation(fadeOut)
            //startActivity(intent)

            val intent = Intent(baseContext, SplashActivity::class.java)
            startActivity(intent)
        }
    }

    //method: populate spinner with continents
    private fun populateContinents(spinner: Spinner, array: Array<String>){
        val layoutID: Int = android.R.layout.simple_spinner_item
        spinner.adapter = ArrayAdapter(this@MainActivity, layoutID, array)
    }
}