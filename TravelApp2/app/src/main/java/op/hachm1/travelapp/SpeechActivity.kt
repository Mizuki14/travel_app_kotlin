package op.hachm1.travelapp

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import java.util.*

class SpeechActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_CODE_STT = 1
    }

    private lateinit var btnStt: MaterialButton
    private lateinit var btnTss: MaterialButton
    private lateinit var languageSpinner: Spinner
    private lateinit var editText: EditText
    private lateinit var tts: TextToSpeech
    private lateinit var bottonNavigationView: BottomNavigationView

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech)
        languageSpinner = findViewById(R.id.languageSpinner)
        populateCountry(languageSpinner, resources.getStringArray(R.array.languageList))

        //button for speech recognition
        btnStt = findViewById(R.id.btn_stt)
        languageSpinner = findViewById(R.id.languageSpinner)
            btnStt.setOnClickListener {// Get the Intent action
                val sttIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                // Language model defines the purpose, there are special models for other use cases, like search.
                sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                when(languageSpinner.selectedItem){
                    // Adding an extra language, you can use any language from the Locale class.
                    "Please select a language" -> Toast.makeText(applicationContext, "Default setting is US ", Toast.LENGTH_SHORT).show()
                    "South Africa" -> sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "nso-ZA")
                    "Nigeria" -> sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "yo-NG")
                    "Japan" -> sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ja")
                    "South Korea" -> sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko")
                    "Australia" -> sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-AU")
                    "New Zealand" -> sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-NZ")
                    "Germany" -> sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "de")
                    "France" -> sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fr")
                    "Canada" -> sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "iu-Cans-CA")
                    "United States of America" ->  sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-us")
                }

                // Text that shows up on the Speech input prompt.
                sttIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now!")
                try {
                    // Start the intent for a result, and pass in our request code.
                    startActivityForResult(sttIntent, REQUEST_CODE_STT)
                } catch (e: ActivityNotFoundException) {
                    // Handling error when the service is not available.
                    e.printStackTrace()
                    Toast.makeText(this, "Your device does not support STT.", Toast.LENGTH_LONG).show()
                }
            }

            //Text to Speech
            btnTss = findViewById(R.id.btn_tts)
            editText = findViewById(R.id.et_text_input)
            editText.setHintTextColor(resources.getColor(R.color.colorAccent))
            languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener { override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val spinnerParent = parent as Spinner
                    tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
                        if (status == TextToSpeech.SUCCESS) {
                            tts.language = Locale.US
                            when (spinnerParent.selectedItem.toString()) {
                                "Please select a language" -> Toast.makeText(applicationContext, "Default setting is US ", Toast.LENGTH_SHORT).show()
                                "South Africa" -> Toast.makeText(applicationContext, "South Africa is only supported for STT", Toast.LENGTH_SHORT).show()
                                "Nigeria" -> Toast.makeText(applicationContext, "Nigeria is only supported for STT", Toast.LENGTH_SHORT).show()
                                "Japan" -> tts.language = Locale.JAPANESE
                                "South Korea" -> tts.language = Locale.KOREAN
                                "Australia" -> tts.language = Locale.getDefault()
                                "New Zealand" -> tts.language = Locale.getDefault()
                                "Germany" -> tts.language = Locale.GERMANY
                                "France" -> tts.language = Locale.FRANCE
                                "Canada" -> tts.language = Locale.CANADA
                                "United States of America" -> Locale.US
                            }
                        }
                    })
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(applicationContext, "No item selected", Toast.LENGTH_SHORT).show()
                }
            }
            //button click for text to speech (TTS)
            btnTss.setOnClickListener { speakOut() }

            //bottom navigation
            bottonNavigationView = findViewById(R.id.bottom_navigation)
            bottonNavigationView.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.back -> {
                        val intent = Intent(baseContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                true
            }
        }
    //get results for speech recognition
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            // Handle the result for our request code.
            REQUEST_CODE_STT -> {
                // Safety checks to ensure data is available.
                if (resultCode == Activity.RESULT_OK && data != null) {
                    // Retrieve the result array.
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    // Ensure result array is not null or empty to avoid errors.
                    if (!result.isNullOrEmpty()) {
                        // Recognized text is in the first position.
                        val recognizedText = result[0]
                        // Do what you want with the recognized text.
                        editText.setText(recognizedText)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        // Shutdown TTS
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
    //this is for text to speech
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun speakOut() {
        val text = editText.text.toString()
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null,null)

    }

    //For populating  country spinner so the user can choose the language
    private fun populateCountry(spinner: Spinner, array: Array<String>) {
        val layoutID: Int = android.R.layout.simple_spinner_item
        spinner.adapter = ArrayAdapter(this, layoutID, array)
    }
}