package com.harismexis.creditscore.framework.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harismexis.creditscore.framework.data.local.table.LocalCreditReport

@Dao
interface CreditInfoLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: LocalCreditReport)

    @Query("SELECT * FROM credit_report_table LIMIT 1")
    suspend fun getCreditReport(): LocalCreditReport?

    @Query("DELETE FROM credit_report_table")
    suspend fun delete()
}
