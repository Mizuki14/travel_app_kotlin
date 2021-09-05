package op.hachm1.travelapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var bottonNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Bottom Navigation
        bottonNavigationView = findViewById(R.id.bottom_navigation)
        bottonNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.exit -> onBackPressed()
                R.id.map -> { val intent = Intent(baseContext, MapsActivity::class.java)
                    startActivity(intent)
                    finish()}
                R.id.speech -> {val intent = Intent(baseContext, SpeechActivity::class.java)
                    startActivity(intent)
                    finish()}
                R.id.quiz ->{val intent = Intent(baseContext, QuizActivity::class.java)
                    startActivity(intent)
                    finish()}
                R.id.settings -> { val intent = Intent(baseContext, SettingActivity::class.java)
                    startActivity(intent)
                    finish()}
            }
            true
        }
    }

    //Exit dialog fragment
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure to exit?")
        builder.setCancelable(true)
        builder.setNegativeButton("No"
        ) { dialogInterface, i ->
            dialogInterface.cancel()
        }
        builder.setPositiveButton("Exit", DialogInterface.OnClickListener { dialogInterface, i ->
            finish()
        })

        val alertDialog = builder.create()
        alertDialog.show()
    }
}