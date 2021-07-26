package com.harismexis.creditscore.framework.data.local.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credit_report_table")
data class LocalCreditReport(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "score") val score: Int,
    @ColumnInfo(name = "maxScoreValue") val maxScoreValue: Int,
)
