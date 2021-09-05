package op.hachm1.travelapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class TranslateActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var inputLanguage: Spinner
    private lateinit var outputLanguage:Spinner
    private lateinit var inputEditText: EditText
    private lateinit var outputEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)

        inputEditText = findViewById(R.id.inputEdit)
        val inputText = inputEditText.toString()

        outputEditText = findViewById(R.id.outputEdit)
        val outputText = outputEditText.toString()

        inputLanguage = findViewById(R.id.inputLanguage)
        populateCountry(inputLanguage, resources.getStringArray(R.array.languageList))
        inputLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener { override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val spinnerParent = parent as Spinner
                    when (spinnerParent.selectedItem.toString()) {
                        "Please select a language" -> Toast.makeText(applicationContext, "Default setting is US ", Toast.LENGTH_SHORT).show()
                        "South Africa" -> Toast.makeText(applicationContext, "South Africa is only supported for STT", Toast.LENGTH_SHORT).show()
                        "Nigeria" -> Toast.makeText(applicationContext, "Nigeria is only supported for STT", Toast.LENGTH_SHORT).show()
        /*                "Japan" -> tts.language = Locale.JAPANESE
                        "South Korea" -> tts.language = Locale.KOREAN
                        "Australia" -> tts.language = Locale.getDefault()
                        "New Zealand" -> tts.language = Locale.getDefault()
                        "Germany" -> tts.language = Locale.GERMANY
                        "France" -> tts.language = Locale.FRANCE
                        "Canada" -> tts.language = Locale.CANADA
                        "United States of America" -> Locale.US*/
                    }
                }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(applicationContext, "No item selected", Toast.LENGTH_SHORT).show()
            }
        }

        outputLanguage = findViewById(R.id.outputLanguage)
        populateCountry(outputLanguage, resources.getStringArray(R.array.languageList))
        outputLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener { override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val spinnerParent = parent as Spinner
            when (spinnerParent.selectedItem.toString()) {
                "Please select a language" -> Toast.makeText(applicationContext, "Default setting is US ", Toast.LENGTH_SHORT).show()
                "South Africa" -> Toast.makeText(applicationContext, "South Africa is only supported for STT", Toast.LENGTH_SHORT).show()
                "Nigeria" -> Toast.makeText(applicationContext, "Nigeria is only supported for STT", Toast.LENGTH_SHORT).show()
                /*                "Japan" -> tts.language = Locale.JAPANESE
                                "South Korea" -> tts.language = Locale.KOREAN
                                "Australia" -> tts.language = Locale.getDefault()
                                "New Zealand" -> tts.language = Locale.getDefault()
                                "Germany" -> tts.language = Locale.GERMANY
                                "France" -> tts.language = Locale.FRANCE
                                "Canada" -> tts.language = Locale.CANADA
                                "United States of America" -> Locale.US*/
            }
        }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(applicationContext, "No item selected", Toast.LENGTH_SHORT).show()
            }
        }


        //bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.back -> {val intent = Intent(baseContext, MainActivity::class.java)
                startActivity(intent)
                        finish()}
            }
            true
        }
    }

    //For populating  country spinner so the user can choose the language
    private fun populateCountry(spinner: Spinner, array: Array<String>) {
        val layoutID: Int = android.R.layout.simple_spinner_item
        spinner.adapter = ArrayAdapter(this, layoutID, array)
    }
}