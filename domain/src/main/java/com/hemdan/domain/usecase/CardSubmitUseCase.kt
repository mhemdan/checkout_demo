package com.hemdan.domain.usecase

import com.hemdan.domain.DispatcherProvider
import com.hemdan.domain.model.CardInfo
import com.hemdan.domain.repository.CardSubmitRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Card submit use case
 *
 * @property dispatcherProvider
 * @property repository
 * @constructor Create empty Card submit use case
 */
class CardSubmitUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: CardSubmitRepository
) {
    /**
     * Invoke
     *
     * @param cardInfo
     * @return CardSubmitResult with success or error
     */
    suspend operator fun invoke(cardInfo: CardInfo): CardSubmitResult {
        return withContext(dispatcherProvider.io()) {
            try {
                CardSubmitResult.Success(repository.submit(cardInfo))
            } catch (e: Exception) {
                CardSubmitResult.Error(e)
            }
        }
    }

    /**
     * Card submit result
     *
     * @constructor Create empty Card submit result
     */
    sealed class CardSubmitResult {
        /**
         * Success
         *
         * @property url
         * @constructor Create Success with url
         */
        data class Success(val url: String) : CardSubmitResult()

        /**
         * Error
         *
         * @property e which is an exception
         * @constructor Create Error with exception
         */
        data class Error(val e: Exception) : CardSubmitResult()
    }
}
