[app](../../index.md) / [op.hachm1.travelapp](../index.md) / [DBHelper](./index.md)

# DBHelper

`class DBHelper : `[`SQLiteOpenHelper`](https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper.html)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DBHelper(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`)` |

### Functions

| Name | Summary |
|---|---|
| [insert](insert.md) | `fun insert(msg: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, int: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [onCreate](on-create.md) | `fun onCreate(db: `[`SQLiteDatabase`](https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onUpgrade](on-upgrade.md) | `fun onUpgrade(db: `[`SQLiteDatabase`](https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html)`, p1: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, p2: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [COLUMN_ID](-c-o-l-u-m-n_-i-d.md) | `const val COLUMN_ID: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [COLUMN_NAME](-c-o-l-u-m-n_-n-a-m-e.md) | `const val COLUMN_NAME: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [COLUMN_NAME_TWO](-c-o-l-u-m-n_-n-a-m-e_-t-w-o.md) | `const val COLUMN_NAME_TWO: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [DATABASE_CREATE](-d-a-t-a-b-a-s-e_-c-r-e-a-t-e.md) | `const val DATABASE_CREATE: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [DATABASE_NAME](-d-a-t-a-b-a-s-e_-n-a-m-e.md) | `const val DATABASE_NAME: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [DATABASE_VERSION](-d-a-t-a-b-a-s-e_-v-e-r-s-i-o-n.md) | `const val DATABASE_VERSION: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [TABLE_NAME](-t-a-b-l-e_-n-a-m-e.md) | `const val TABLE_NAME: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
