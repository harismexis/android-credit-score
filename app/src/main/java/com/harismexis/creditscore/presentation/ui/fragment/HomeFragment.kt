package com.harismexis.creditscore.presentation.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.harismexis.creditscore.R
import com.harismexis.creditscore.core.domain.CreditReport
import com.harismexis.creditscore.core.result.CreditResult
import com.harismexis.creditscore.databinding.DonutBinding
import com.harismexis.creditscore.databinding.FragmentHomeBinding
import com.harismexis.creditscore.framework.animation.ProgressBarAnimation
import com.harismexis.creditscore.framework.event.EventObserver
import com.harismexis.creditscore.presentation.ui.base.BaseFragment
import com.harismexis.creditscore.presentation.viewmodel.HomeViewModel
import java.lang.ref.WeakReference

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
    private var binding: FragmentHomeBinding? = null
    private var donut: DonutBinding? = null

    companion object {
        const val ANIM_DURATION = 3000L
    }

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        donut = binding?.donutView
    }

    override fun onCreateView() {
        setupSwipeToRefresh()
    }

    override fun onViewCreated() {
        observeLiveData()
        viewModel.getCreditReport()
    }

    override fun getRootView() = binding?.root

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun observeLiveData() {
        viewModel.credit.observe(viewLifecycleOwner, {
            when (it) {
                is CreditResult.Success -> populate(it.creditReport)
                is CreditResult.Error -> populateError(it.error)
            }
        })
        viewModel.canRefresh.observe(viewLifecycleOwner, {
            binding?.homeSwipeRefresh?.isEnabled = it
        })
        viewModel.showSnack.observe(viewLifecycleOwner, EventObserver { errorMsg ->
            showSnackBar(errorMsg)
        })
    }

    private fun populate(uiModel: CreditReport) {
        stopLoading()
        donut?.let {
            it.scoreProgressBar.max = uiModel.maxScoreValue
            it.txtFooter.text = getString(
                R.string.credit_score_footer,
                uiModel.maxScoreValue.toString()
            )
            animateScore(uiModel.score)
        }
    }

    private fun populateError(e: Exception) {
        stopLoading()
        val questionMark = getString(R.string.question_mark)
        donut?.let {
            it.scoreProgressBar.progress = 0
            it.scoreProgressBar.max = 0
            it.txtCreditScore.text = questionMark
            it.txtFooter.text = getString(
                R.string.credit_score_footer,
                questionMark
            )
        }
    }

    private fun stopLoading() {
        binding?.homeSwipeRefresh?.isRefreshing = false
        binding?.loadingProgressBar?.visibility = View.GONE
    }

    private fun showSnackBar(msg: String) {
        binding?.homeCoordinator?.let { layout ->
            val snack = Snackbar.make(layout, msg, Snackbar.LENGTH_LONG)
            snack.show()
        }
    }

    private fun animateScore(userScore: Int) {
        val anim = ProgressBarAnimation(
            WeakReference(donut?.scoreProgressBar),
            WeakReference(donut?.txtCreditScore),
            0.0f,
            userScore.toFloat()
        )
        anim.duration = ANIM_DURATION
        donut?.scoreProgressBar?.startAnimation(anim)
    }

    private fun setupSwipeToRefresh() {
        binding?.homeSwipeRefresh?.setOnRefreshListener {
            binding?.homeSwipeRefresh?.isRefreshing = true
            viewModel.getCreditReport()
        }
    }


}
