package com.hemdan.presentation.ui.cardsubmit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemdan.domain.model.CardInfo
import com.hemdan.domain.usecase.CardSubmitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CardSubmitViewModel @Inject constructor(private val cardVerifyUseCase: CardSubmitUseCase) :
    ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Idle)
    val viewState = _viewState

    fun verifyCard(cardNumber: String, expiryDate: String, cvv: String) {
        viewModelScope.launch {
            _viewState.update {
                ViewState.Loading
            }
            when (val result = cardVerifyUseCase.invoke(
                CardInfo(
                    cardNumber = cardNumber,
                    cvv = cvv,
                    expiryMonth = expiryDate.take(2),
                    expiryYear = expiryDate.takeLast(2)
                )
            )) {
                is CardSubmitUseCase.CardSubmitResult.Success -> {
                    _viewState.update {
                        ViewState.Success(result.url)
                    }
                }
                is CardSubmitUseCase.CardSubmitResult.Error -> {
                    _viewState.update {
                        ViewState.Error
                    }
                }
            }
        }
    }

    sealed class ViewState {
        object Idle : ViewState()
        object Loading : ViewState()
        data class Success(val url: String) : ViewState()
        object Error : ViewState()
    }
}