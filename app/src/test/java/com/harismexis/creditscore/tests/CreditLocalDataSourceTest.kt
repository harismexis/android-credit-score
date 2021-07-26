package com.harismexis.creditscore.tests

import com.harismexis.creditscore.framework.data.local.dao.CreditInfoLocalDao
import com.harismexis.creditscore.framework.data.local.datasource.CreditLocalDataSource
import com.harismexis.creditscore.framework.extensions.toLocalItem
import com.harismexis.creditscore.setup.UnitTestSetup
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class CreditLocalDataSourceTest : UnitTestSetup() {

    @Mock
    private lateinit var mockDao: CreditInfoLocalDao

    private lateinit var subject: CreditLocalDataSource

    @Before
    fun initialiseClassUnderTest() {
        subject = CreditLocalDataSource(mockDao)
    }

    @Test
    fun savesItem_savedExpectedItem() {
        runBlocking {
            // given
            val mock = mockProvider.getMockCreditReport()

            // when
            subject.save(mock)

            // then
            verify(mockDao, times(1)).insert(mock.toLocalItem())
        }
    }

    @Test
    fun getSavedItem_gotExpectedItem() {
        runBlocking {
            // given
            val mock = mockProvider.getMockCreditReport()
            `when`(mockDao.getCreditReport()).thenReturn(mock.toLocalItem())

            // when
            val actual = subject.getCreditReport()

            // then
            verify(mockDao, times(1)).getCreditReport()
            Assert.assertEquals(mock.score, actual!!.score)
            Assert.assertEquals(mock.maxScoreValue, actual.maxScoreValue)
        }
    }
}