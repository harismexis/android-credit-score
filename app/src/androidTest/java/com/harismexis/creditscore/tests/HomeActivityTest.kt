package com.harismexis.creditscore.tests

import android.widget.ProgressBar
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.harismexis.creditscore.R
import com.harismexis.creditscore.core.result.CreditResult
import com.harismexis.creditscore.presentation.ui.activity.HomeActivity
import com.harismexis.creditscore.presentation.ui.fragment.HomeFragment
import com.harismexis.creditscore.setup.InstrumentedTestSetup
import com.harismexis.creditscore.setup.util.getString
import com.harismexis.creditscore.setup.util.getStringFormatted
import com.harismexis.creditscore.setup.viewmodel.mockCreditResult
import com.harismexis.creditscore.setup.viewmodel.mockVm
import io.mockk.every
import org.hamcrest.core.IsNot
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest: InstrumentedTestSetup() {

    @Before
    fun setup() {
        every { mockVm.credit } returns mockCreditResult
    }

    private fun launchActivityUnderTest(): ActivityScenario<HomeActivity> {
        return launchActivity()
    }

    @Test
    fun viewModelEmitsCreditReport_uiShowsExpectedData() {
        // given
        val mockCreditScore = mockProvider.getMockCreditReport()
        val expectedScore = mockCreditScore.score
        val expectedMaxScore = mockCreditScore.maxScoreValue

        val expectedHeader = getString(R.string.credit_score_header)
        val expectedFooter = getStringFormatted(R.string.credit_score_footer,
            expectedMaxScore.toString())

        every { mockVm.getCreditReport() } answers {
            mockCreditResult.value = CreditResult.Success(mockCreditScore)
        }

        // when
        val scenario = launchActivityUnderTest()

        // then
        onView(withId(R.id.loadingProgressBar)).check(matches(IsNot(isDisplayed())))
        onView(withId(R.id.donutView)).check(matches(isDisplayed()))
        onView(withId(R.id.txtHeader)).check(matches(withText(expectedHeader)))
        onView(withId(R.id.txtFooter)).check(matches(withText(expectedFooter)))
        scenario.onActivity {
            val scoreProgressBar = it.findViewById<ProgressBar>(R.id.scoreProgressBar)
            assertEquals(expectedMaxScore, scoreProgressBar.max)
        }

        // Wait for Animation to finish
        Thread.sleep(HomeFragment.ANIM_DURATION + 100)

        // Check if expectedScore is displayed
        onView(withId(R.id.txtCreditScore)).check(matches(withText(expectedScore.toString())))
    }

    @Test
    fun viewModelEmitsError_uiShowsExpectedData() {
        // given
        val expectedHeader = getString(R.string.credit_score_header)
        val expectedScore = getString(R.string.question_mark)
        val expectedFooter = getStringFormatted(R.string.credit_score_footer,
            getString(R.string.question_mark)
        )

        every { mockVm.getCreditReport() } answers {
            mockCreditResult.value = CreditResult.Error(Exception())
        }

        val scenario = launchActivityUnderTest()

        // then
        onView(withId(R.id.loadingProgressBar)).check(matches(IsNot(isDisplayed())))
        onView(withId(R.id.donutView)).check(matches(isDisplayed()))
        onView(withId(R.id.txtHeader)).check(matches(withText(expectedHeader)))
        onView(withId(R.id.txtFooter)).check(matches(withText(expectedFooter)))
        scenario.onActivity {
            val scoreProgressBar = it.findViewById<ProgressBar>(R.id.scoreProgressBar)
            assertEquals(0, scoreProgressBar.max)
        }

        onView(withId(R.id.txtCreditScore)).check(matches(withText(expectedScore)))
    }

}
