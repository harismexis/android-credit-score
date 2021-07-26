package com.harismexis.creditscore.framework.data.local.schema

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.harismexis.creditscore.framework.data.local.dao.CreditInfoLocalDao
import com.harismexis.creditscore.framework.data.local.table.LocalCreditReport

@Database(
    entities = [
        LocalCreditReport::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CreditInfoDatabase : RoomDatabase() {

    companion object {
        @Volatile
        var INSTANCE: CreditInfoDatabase? = null
        private const val DB_FILE = "credit_info_room_database"

        fun getDatabase(
            context: Context
        ): CreditInfoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CreditInfoDatabase::class.java,
                    DB_FILE
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getCreditInfoLocalDao(): CreditInfoLocalDao

}
