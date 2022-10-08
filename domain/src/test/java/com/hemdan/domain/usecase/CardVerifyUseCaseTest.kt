@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hemdan.domain.usecase

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.decorator.optional.NeverOptionalStrategy
import com.appmattus.kotlinfixture.decorator.optional.optionalStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.hemdan.domain.DispatcherProvider
import com.hemdan.domain.FAILURE_CALLBACK_URL
import com.hemdan.domain.SUCCESS_CALLBACK_URL
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class CardVerifyUseCaseTest {
    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
        optionalStrategy(NeverOptionalStrategy)
    }
    private val dispatcherProvider: DispatcherProvider = mockk()
    private val subject = CardVerifyUseCase(dispatcherProvider)

    @Test
    fun `invoke - should return isSubmit = true - success url provided`() = runTest {
        val expected = CardVerifyUseCase.CardVerifyResult.SubmitState(isSubmitted = true)
        every { dispatcherProvider.io() } returns StandardTestDispatcher(testScheduler)
        val actual = subject(SUCCESS_CALLBACK_URL)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `invoke - should return isSubmit = false - failure url provided`() = runTest {
        val expected = CardVerifyUseCase.CardVerifyResult.SubmitState(isSubmitted = false)
        every { dispatcherProvider.io() } returns StandardTestDispatcher(testScheduler)
        val actual = subject(FAILURE_CALLBACK_URL)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `invoke - should return isSubmit = null - not failure url nor success url provided`() = runTest {
        val otherUrl = fixture<String>()
        val expected = CardVerifyUseCase.CardVerifyResult.SubmitState(isSubmitted = null)
        every { dispatcherProvider.io() } returns StandardTestDispatcher(testScheduler)
        val actual = subject(otherUrl)
        assertThat(actual).isEqualTo(expected)
    }
}