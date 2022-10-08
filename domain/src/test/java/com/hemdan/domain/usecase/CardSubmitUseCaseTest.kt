@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hemdan.domain.usecase

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.decorator.optional.NeverOptionalStrategy
import com.appmattus.kotlinfixture.decorator.optional.optionalStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.hemdan.domain.DispatcherProvider
import com.hemdan.domain.model.CardInfo
import com.hemdan.domain.repository.CardSubmitRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CardSubmitUseCaseTest {
    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
        optionalStrategy(NeverOptionalStrategy)
    }
    private val repository: CardSubmitRepository = mockk()
    private val dispatcherProvider: DispatcherProvider = mockk()
    private val subject = CardSubmitUseCase(dispatcherProvider, repository)

    @Test
    fun `invoke - should return success - valid card info provided`() = runTest {
        val cardInfo: CardInfo = fixture()
        val url: String = fixture()
        val expected = CardSubmitUseCase.CardSubmitResult.Success(url)
        every { dispatcherProvider.io() } returns StandardTestDispatcher(testScheduler)
        coEvery { repository.submit(cardInfo) } returns url

        val actual = subject(cardInfo)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `invoke - should return error - invalid card info provided`() = runTest {
        val cardInfo: CardInfo = fixture()
        val exception: Exception = fixture()
        val expected = CardSubmitUseCase.CardSubmitResult.Error(exception)
        every { dispatcherProvider.io() } returns StandardTestDispatcher(testScheduler)
        coEvery { repository.submit(cardInfo) } throws exception

        val actual = subject(cardInfo)

        assertThat(actual).isEqualTo(expected)
    }
}