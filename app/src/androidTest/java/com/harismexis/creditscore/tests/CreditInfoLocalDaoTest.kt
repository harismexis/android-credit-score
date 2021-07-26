package com.harismexis.creditscore.tests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.harismexis.creditscore.framework.data.local.dao.CreditInfoLocalDao
import com.harismexis.creditscore.framework.data.local.schema.CreditInfoDatabase
import com.harismexis.creditscore.framework.extensions.toLocalItem
import com.harismexis.creditscore.setup.InstrumentedTestSetup
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CreditInfoLocalDaoTest: InstrumentedTestSetup() {

    private lateinit var dao: CreditInfoLocalDao
    private lateinit var database: CreditInfoDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, CreditInfoDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.getCreditInfoLocalDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun savingItem_expectedItemRetrieved() = runBlocking {
        // given
        val item = mockProvider.getMockCreditReport()

        // when
        dao.insert(item.toLocalItem())
        val savedItem = dao.getCreditReport()

        // then
        Assert.assertNotNull(savedItem)
        Assert.assertEquals(item.score, savedItem!!.score)
        Assert.assertEquals(item.maxScoreValue, savedItem.maxScoreValue)
    }

}
