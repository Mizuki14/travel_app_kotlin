package op.hachm1.travelapp

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.preference.PreferenceManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingActivity : AppCompatActivity() {

    private lateinit var bottonNavigationView :BottomNavigationView
    private lateinit var sharedPreferences: SharedPreferences
    private var isNightModeEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        //dark mode
        val darkmodeSwitch = findViewById<SwitchCompat>(R.id.darkmode)
        darkmodeSwitch.setOnClickListener{
            var isDark = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            //Reverse the theme
            when(isDark)
            {
                Configuration.UI_MODE_NIGHT_YES ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                Configuration.UI_MODE_NIGHT_NO ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        //bottom navigation
        bottonNavigationView = findViewById(R.id.bottom_navigation)
        bottonNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.back -> {
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            true
        }


    }
}