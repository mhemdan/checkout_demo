@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hemdan.data.repository;

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.decorator.optional.NeverOptionalStrategy
import com.appmattus.kotlinfixture.decorator.optional.optionalStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.hemdan.data.datasource.remote.VerifyCardApi
import com.hemdan.data.mapper.toRequest
import com.hemdan.data.model.VerifyCardResponse
import com.hemdan.domain.model.CardInfo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CardSubmitRepositoryImplTest {

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
        optionalStrategy(NeverOptionalStrategy)
    }
    private val api: VerifyCardApi = mockk()
    private val subject = CardSubmitRepositoryImpl(api)

    @Test
    fun `submit - should return url - valid card info provided`() = runTest {
        val cardInfo = fixture<CardInfo>()
        val response = fixture<VerifyCardResponse>()
        val expected = response.url
        coEvery { api.verify(cardInfo.toRequest()) } returns response

        val actual = subject.submit(cardInfo)
        assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun `submit - should throw exception - invalid card info provided`() = runTest {
        val cardInfo = fixture<CardInfo>()
        coEvery { api.verify(cardInfo.toRequest()) } throws Exception()

        subject.submit(cardInfo)
    }

}
