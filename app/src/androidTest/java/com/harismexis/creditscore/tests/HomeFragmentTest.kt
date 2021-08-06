package com.harismexis.creditscore.tests

import android.widget.ProgressBar
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.harismexis.creditscore.R
import com.harismexis.creditscore.core.result.CreditResult
import com.harismexis.creditscore.presentation.home.fragment.HomeFragment
import com.harismexis.creditscore.setup.InstrumentedSetup
import com.harismexis.creditscore.setup.util.getString
import com.harismexis.creditscore.setup.util.getStringFormatted
import com.harismexis.creditscore.setup.viewmodel.mockCreditResult
import com.harismexis.creditscore.setup.viewmodel.mockVm
import io.mockk.every
import junit.framework.Assert.assertEquals
import org.hamcrest.core.IsNot
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest: InstrumentedSetup() {

    @Before
    fun setup() {
        every { mockVm.credit } returns mockCreditResult
    }

    private fun launchFragmentUnderTest() : FragmentScenario<HomeFragment> {
        return launchFragmentInContainer()
    }

    @Test
    fun viewModelEmitsCreditReport_uiShowsExpectedData() {
        // given
        val mockCreditScore = mockProvider.getMockCreditReport()
        val expectedScore = mockCreditScore.score
        val expectedMaxScore = mockCreditScore.maxScoreValue
        val expectedHeader = getString(R.string.score_header)
        val expectedFooter = getStringFormatted(R.string.score_footer,
            expectedMaxScore.toString())

        every { mockVm.getCreditReport() } answers {
            mockCreditResult.value = CreditResult.Success(mockCreditScore)
        }

        // when
        val scenario = launchFragmentUnderTest()

        // then
        onView(withId(R.id.loadingProgressBar)).check(matches(IsNot(isDisplayed())))
        onView(withId(R.id.donutView)).check(matches(isDisplayed()))
        onView(withId(R.id.txtHeader)).check(matches(withText(expectedHeader)))
        onView(withId(R.id.txtFooter)).check(matches(withText(expectedFooter)))
        scenario.onFragment {
            val scoreProgressBar = it.view!!.findViewById<ProgressBar>(R.id.scoreProgressBar)
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
        val expectedHeader = getString(R.string.score_header)
        val expectedScore = getString(R.string.question_mark)
        val expectedFooter = getStringFormatted(R.string.score_footer,
            getString(R.string.question_mark)
        )

        every { mockVm.getCreditReport() } answers {
            mockCreditResult.value = CreditResult.Error(Exception())
        }

        // when
        val scenario = launchFragmentUnderTest()

        // then
        onView(withId(R.id.loadingProgressBar)).check(matches(IsNot(isDisplayed())))
        onView(withId(R.id.donutView)).check(matches(isDisplayed()))
        onView(withId(R.id.txtHeader)).check(matches(withText(expectedHeader)))
        onView(withId(R.id.txtFooter)).check(matches(withText(expectedFooter)))
        scenario.onFragment {
            val scoreProgressBar = it.view!!.findViewById<ProgressBar>(R.id.scoreProgressBar)
            assertEquals(0, scoreProgressBar.max)
        }

        onView(withId(R.id.txtCreditScore)).check(matches(withText(expectedScore)))
    }

}
