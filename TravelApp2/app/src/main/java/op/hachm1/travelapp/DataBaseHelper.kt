package op.hachm1.travelapp
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.collections.ArrayList

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    //insert method
    fun insert(msg: String, int: Int): Long {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, msg)
        values.put(COLUMN_NAME_TWO, int)
        val id: Long = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    /*fun selectAll(): ArrayList<Score> {
        val scores = ArrayList<Score>()
        val selectQuery = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_NAME_TWO ASC LIMIT 5"
        val db: SQLiteDatabase = this.writableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val score = Score()
                score.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                score.countryName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                score.score = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_TWO))
                scores.add(score)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return scores
    } */

    //create object for database
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "db_quiz"
        const val TABLE_NAME = "quiz"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "countryName"
        const val COLUMN_NAME_TWO = "score"
        const val DATABASE_CREATE: String =
            "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT, $COLUMN_NAME_TWO STRING)"
    }
}