package op.hachm1.travelapp
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton


class QuizActivity : AppCompatActivity() {
    private lateinit var scores: ArrayList<Score>
    private lateinit var trueButton: MaterialButton
    private lateinit var falseButton: MaterialButton
    private lateinit var quizSpinner: Spinner
    private lateinit var bottonNavigationView: BottomNavigationView
    private lateinit var textView: TextView
    private lateinit var dbHelper: DBHelper
    private lateinit var imageView: ImageView

    var numQuiz = 0
    var score = 0
    var selectedCountry =""

    //get string array from string.xml
    var quizQuestion = arrayOfNulls<String>(5)
    var quizAnswer = arrayOfNulls<String>(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        scores = ArrayList()
        dbHelper = DBHelper(this@QuizActivity)

        quizSpinner = findViewById(R.id.quizSpinner)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        imageView = findViewById(R.id.imageView)
        populateCountry(quizSpinner, resources.getStringArray(R.array.quizList))

        //This will populate quiz based on selected country
        quizSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //set up quiz including put associated image and show buttons
                val spinnerParent = parent as Spinner
                when (spinnerParent.selectedItem.toString()) {
                    "Choose a country to start quiz" -> {
                        trueButton.isVisible = false;falseButton.isVisible = false
                    }
                    "South Africa" -> {
                        quizQuestion =
                            resources.getStringArray(R.array.SouthAfricaQuiz); quizAnswer =
                            resources.getStringArray(
                                R.array.SouthAfricaAnswer
                            );trueButton.isVisible = true;falseButton.isVisible =
                            true;quizSpinner.isVisible = false;imageView.setImageResource(
                            R.drawable.southafricaflag
                        )
                    }
                    "Japan" -> {
                        quizQuestion = resources.getStringArray(R.array.JapanQuiz); quizAnswer =
                            resources.getStringArray(
                                R.array.JapanAnswer
                            );trueButton.isVisible = true;falseButton.isVisible =
                            true;quizSpinner.isVisible = false;imageView.setImageResource(
                            R.drawable.japanflag
                        )
                    }
                    "New Zealand" -> {
                        quizQuestion = resources.getStringArray(R.array.NZQuiz); quizAnswer =
                            resources.getStringArray(
                                R.array.NZAnswer
                            );trueButton.isVisible = true;falseButton.isVisible =
                            true;quizSpinner.isVisible = false;imageView.setImageResource(
                            R.drawable.newzealandflag
                        )
                    }
                    "Germany" -> {
                        quizQuestion = resources.getStringArray(R.array.GermanyQuiz); quizAnswer =
                            resources.getStringArray(
                                R.array.GermanyAnswer
                            );trueButton.isVisible = true;falseButton.isVisible =
                            true;quizSpinner.isVisible = false;imageView.setImageResource(
                            R.drawable.germanyflag
                        )
                    }
                    "Canada" -> {
                        quizQuestion = resources.getStringArray(R.array.CanadaQuiz); quizAnswer =
                            resources.getStringArray(
                                R.array.CanadaAnswer
                            );trueButton.isVisible = true;falseButton.isVisible =
                            true;quizSpinner.isVisible = false;imageView.setImageResource(
                            R.drawable.canadaflag
                        )
                    }
                    "Brazil" -> {
                        quizQuestion = resources.getStringArray(R.array.BrazilQuiz); quizAnswer =
                            resources.getStringArray(
                                R.array.BrazilAnswer
                            );trueButton.isVisible = true;falseButton.isVisible =
                            true;quizSpinner.isVisible = false;imageView.setImageResource(
                            R.drawable.brazilflag
                        )
                    }
                }
                //this is game engine
                selectedCountry = spinnerParent.selectedItem.toString()
                textView = findViewById(R.id.textView)
                textView.text = quizQuestion[numQuiz]
                    trueButton.setOnClickListener{
                        if (numQuiz < quizQuestion.size){
                            textView.text = quizQuestion[numQuiz]
                            //check if user answer matches the answer
                            if (quizAnswer[numQuiz] == "True") {
                                Toast.makeText(baseContext, "Correct", Toast.LENGTH_SHORT).show()
                                score += 1
                            }
                            else{
                                Toast.makeText(
                                    baseContext, "Incorrect, Answer was False", Toast.LENGTH_SHORT
                                ).show()
                            }
                            numQuiz += 1
                        }
                        else{
                            //insert  score to db
                            dbHelper.insert(selectedCountry, score)
                            //show score
                            readDatabase()
                            trueButton.isVisible = false
                            falseButton.isVisible = false
                        }
                    }
                    //For false button
                    falseButton.setOnClickListener{
                        //goes to next quiz
                        if (numQuiz<quizQuestion.size){
                            textView.text = quizQuestion[numQuiz]
                            //check if user answer matches the answer
                            if (quizAnswer[numQuiz] == "False") {
                                Toast.makeText(baseContext, "Correct", Toast.LENGTH_SHORT).show()
                                score += 1
                            }
                            else{
                                Toast.makeText(
                                    baseContext, "Incorrect, Answer was True", Toast.LENGTH_SHORT
                                ).show()
                            }
                            numQuiz += 1
                        }
                        else{
                            //insert  score to db
                            dbHelper.insert(selectedCountry, score)
                            //show score
                            readDatabase()
                            trueButton.isVisible = false
                            falseButton.isVisible = false
                        }
                    }
                }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(applicationContext, "No item selected", Toast.LENGTH_SHORT).show()
            }
        }
        //bottom navigation
        bottonNavigationView = findViewById(R.id.bottom_navigation)
        bottonNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.back -> onBackPressed()
            }
            true
        }
    }

    //method to get data from database and put it in the text view
    private fun readDatabase() {
        //scores = dbHelper.selectAll()
        //playlistRecyclerViewAdapter.notifyData(score)
        textView = findViewById(R.id.textView)
        textView.text =null
        val selectQuery = "SELECT * FROM ${DBHelper.TABLE_NAME} GROUP BY ${DBHelper.COLUMN_NAME} ORDER BY ${DBHelper.COLUMN_NAME_TWO} DESC LIMIT 3"
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val score = Score()
                score.id = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID))
                score.countryName = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME))
                score.scores = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_NAME_TWO))
                scores.add(score)
                textView.append(cursor.getString(1))
                textView.append(cursor.getString(2))
                textView.append("\n")
            } while (cursor.moveToNext())
        }
    }

    //Dialog ask user to get confirm the exit the quiz
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Exit from quiz?")
        builder.setCancelable(true)
        builder.setNegativeButton("No",
            DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
        builder.setPositiveButton("Exit", DialogInterface.OnClickListener { dialogInterface, i ->
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

        val alertDialog = builder.create()
        alertDialog.show()
    }
    //For populating  country spinner so the user can choose the language
    private fun populateCountry(spinner: Spinner, array: Array<String>) {
        val layoutID: Int = android.R.layout.simple_spinner_item
        spinner.adapter = ArrayAdapter(this, layoutID, array)
    }
}

class Score {
    var id: Int = 0
    var countryName: String? = null
    var scores: Int? = null
}