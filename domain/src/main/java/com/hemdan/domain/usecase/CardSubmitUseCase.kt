package com.hemdan.domain.usecase

import com.hemdan.domain.DispatcherProvider
import com.hemdan.domain.model.CardInfo
import com.hemdan.domain.repository.CardSubmitRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardSubmitUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: CardSubmitRepository
) {
    suspend operator fun invoke(cardInfo: CardInfo): CardSubmitResult {
        return withContext(dispatcherProvider.io()) {
            try {
                CardSubmitResult.Success(repository.submit(cardInfo))
            } catch (e: Exception) {
                CardSubmitResult.Error(e)
            }
        }
    }

    sealed class CardSubmitResult {
        data class Success(val url: String) : CardSubmitResult()
        data class Error(val e: Exception) : CardSubmitResult()
    }
}