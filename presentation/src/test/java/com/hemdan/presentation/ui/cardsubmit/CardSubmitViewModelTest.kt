@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hemdan.presentation.ui.cardsubmit

import app.cash.turbine.test
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.decorator.optional.NeverOptionalStrategy
import com.appmattus.kotlinfixture.decorator.optional.optionalStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.hemdan.domain.usecase.CardSubmitUseCase
import com.hemdan.presentation.ui.MainDispatcherRule
import com.hemdan.presentation.ui.utils.Validator
import io.mockk.called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class CardSubmitViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
        optionalStrategy(NeverOptionalStrategy)
    }
    private val cardSubmitUseCase: CardSubmitUseCase = mockk()
    private val subject = CardSubmitViewModel(cardSubmitUseCase, Validator())

    @Test
    fun `submitCard - should trigger success ViewState with url - valid card info provided`() =
        runTest {
            val result: CardSubmitUseCase.CardSubmitResult.Success = fixture()
            val expected = CardSubmitViewModel.ViewState.Success(result.url)
            coEvery { cardSubmitUseCase(any()) } returns result

            subject.submitCard("4242424242424242", "06/30", "123")

            subject.viewState.test {
                assertThat(awaitItem()).isEqualTo(CardSubmitViewModel.ViewState.Idle)
                assertThat(awaitItem()).isEqualTo(CardSubmitViewModel.ViewState.Loading)
                assertThat(awaitItem()).isEqualTo(expected)
            }
        }

    @Test
    fun `submitCard - should trigger SubmitError ViewState - invalid card info provided`() =
        runTest {
            val result: CardSubmitUseCase.CardSubmitResult.Error = fixture()
            val expected = CardSubmitViewModel.ViewState.SubmitError
            coEvery { cardSubmitUseCase(any()) } returns result

            subject.submitCard("4242424242424242", "06/30", "123")

            subject.viewState.test {
                assertThat(awaitItem()).isEqualTo(CardSubmitViewModel.ViewState.Idle)
                assertThat(awaitItem()).isEqualTo(CardSubmitViewModel.ViewState.Loading)
                assertThat(awaitItem()).isEqualTo(expected)
            }
        }

    @Test
    fun `submitCard - should trigger CardNumberError ViewState  - invalid card number provided`() =
        runTest {
            subject.submitCard("7770 6565 6454 4534", "06/30", "123")

            subject.viewState.test {
                assertThat(awaitItem()).isEqualTo(CardSubmitViewModel.ViewState.Idle)
                assertThat(awaitItem()).isEqualTo(CardSubmitViewModel.ViewState.CardNumberError)
            }

            coVerify { cardSubmitUseCase(any()) wasNot called }
        }
}