@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hemdan.presentation.ui.cardverify

import app.cash.turbine.test
import com.hemdan.domain.FAILURE_CALLBACK_URL
import com.hemdan.domain.SUCCESS_CALLBACK_URL
import com.hemdan.domain.usecase.CardVerifyUseCase
import com.hemdan.presentation.ui.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class CardVerifyViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val cardVerifyUseCase: CardVerifyUseCase = mockk()
    private val subject = CardVerifyViewModel(cardVerifyUseCase)

    @Test
    fun `checkCallbackUrl - should trigger NavigateToSuccessScreen - url with success callback provided`() =
        runTest {
            coEvery { cardVerifyUseCase(any()) } returns CardVerifyUseCase.CardVerifyResult.SubmitState(
                isSubmitted = true
            )
            subject.checkCallbackUrl(SUCCESS_CALLBACK_URL)
            subject.viewState.test {
                assertThat(awaitItem()).isEqualTo(CardVerifyViewModel.ViewState.Idle)
                assertThat(awaitItem()).isEqualTo(CardVerifyViewModel.ViewState.NavigateToSuccessScreen)
            }
        }

    @Test
    fun `checkCallbackUrl - should trigger NavigateToFailureScreen - url with failure callback provided`() =
        runTest {
            coEvery { cardVerifyUseCase(any()) } returns CardVerifyUseCase.CardVerifyResult.SubmitState(
                isSubmitted = false
            )
            subject.checkCallbackUrl(FAILURE_CALLBACK_URL)
            subject.viewState.test {
                assertThat(awaitItem()).isEqualTo(CardVerifyViewModel.ViewState.Idle)
                assertThat(awaitItem()).isEqualTo(CardVerifyViewModel.ViewState.NavigateToFailureScreen)
            }
        }

    @Test
    fun `checkCallbackUrl - should stay idle - invalid url provided (no failure nor success url)`() =
        runTest {
            coEvery { cardVerifyUseCase(any()) } returns CardVerifyUseCase.CardVerifyResult.SubmitState(
                isSubmitted = null
            )
            subject.checkCallbackUrl("url")
            subject.viewState.test {
                assertThat(awaitItem()).isEqualTo(CardVerifyViewModel.ViewState.Idle)
            }
        }
}