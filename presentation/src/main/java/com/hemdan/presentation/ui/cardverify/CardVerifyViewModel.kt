package com.hemdan.presentation.ui.cardverify

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemdan.domain.usecase.CardVerifyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardVerifyViewModel @Inject constructor(private val cardVerifyUseCase: CardVerifyUseCase) :
    ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Idle)
    val viewState = _viewState

    fun checkCallbackUrl(url: String) {
        viewModelScope.launch {
            when (val result = cardVerifyUseCase(url)) {
                is CardVerifyUseCase.CardVerifyResult.Success -> {
                    if(result.isSubmitted == true) {
                        _viewState.update {
                            ViewState.NavigateToSuccessScreen
                        }
                    }else if(result.isSubmitted == false) {
                        _viewState.update {
                            ViewState.NavigateToFailureScreen
                        }
                    }
                }
                else -> {
                    _viewState.update {
                        ViewState.NavigateToFailureScreen
                    }
                }
            }
        }
    }

    sealed interface Navigation
    sealed class ViewState {
        object Idle : ViewState()
        object NavigateToSuccessScreen : ViewState(), Navigation
        object NavigateToFailureScreen : ViewState(), Navigation
    }
}