package com.harismexis.creditscore.tests

import com.harismexis.creditscore.core.domain.CreditReport
import com.harismexis.creditscore.framework.data.local.dao.CreditInfoLocalDao
import com.harismexis.creditscore.framework.data.local.datasource.CreditLocalDataSource
import com.harismexis.creditscore.framework.extensions.toLocalItem
import com.harismexis.creditscore.setup.UnitTestSetup
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CreditLocalDataSourceTest : UnitTestSetup() {

    @MockK
    private lateinit var mockDao: CreditInfoLocalDao

    private lateinit var subject: CreditLocalDataSource

    @Before
    fun doBefore() {
        subject = CreditLocalDataSource(mockDao)
    }

    @Test
    fun savesItem_savedExpectedItem() {
        // given
        val mock = mockProvider.getMockCreditReport()

        // when
        runBlocking {
            subject.save(mock)
        }

        // then
        coVerify { mockDao.insert(mock.toLocalItem()) }
    }

    @Test
    fun getSavedItem_gotExpectedItem() {
        // given
        val mock = mockProvider.getMockCreditReport()
        coEvery { mockDao.getCreditReport() }.returns(mock.toLocalItem())

        // when
        var actual: CreditReport?
        runBlocking {
            actual = subject.getCreditReport()
        }

        // then
        coVerify { mockDao.getCreditReport() }
        Assert.assertEquals(mock.score, actual!!.score)
        Assert.assertEquals(mock.maxScoreValue, actual!!.maxScoreValue)

    }
}