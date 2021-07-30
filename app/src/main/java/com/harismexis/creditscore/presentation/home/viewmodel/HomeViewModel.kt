package com.harismexis.creditscore.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.creditscore.core.data.repository.CreditRepository
import com.harismexis.creditscore.core.result.CreditResult
import com.harismexis.creditscore.framework.event.Event
import com.harismexis.creditscore.framework.extensions.msg
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: CreditRepository,
) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    private val mCredit = MutableLiveData<CreditResult>()
    val credit: LiveData<CreditResult>
        get() = mCredit

    private val mShowSnackBar = MutableLiveData<Event<String>>()
    val showSnackBar: LiveData<Event<String>>
        get() = mShowSnackBar

    private val mCanRefresh = MutableLiveData<Boolean>()
    val canRefresh: LiveData<Boolean>
        get() = mCanRefresh

    init {
        mCanRefresh.value = true
    }

    fun getCreditReport() {
        viewModelScope.launch {
            mCanRefresh.value = false
            try {
                val report = repository.getRemoteCreditReport()
                report?.let {
                    mCredit.value = CreditResult.Success(it)
                    repository.save(it)
                }
            } catch (e: Exception) {
                val errMsg = e.msg()
                Log.d(TAG, errMsg)
                mShowSnackBar.value = Event(errMsg)
                emitCachedCreditReport()
            }
            mCanRefresh.value = true
        }
    }

    private suspend fun emitCachedCreditReport() {
        try {
            val report = repository.getLocalCreditReport()
            if (report != null) {
                mCredit.value = CreditResult.Success(report)
            } else {
                mCredit.value =
                    CreditResult.Error(Exception("No cached CreditScore"))
            }
        } catch (ex: Exception) {
            Log.d(TAG, ex.msg())
            mCredit.value = CreditResult.Error(ex)
        }
    }

}