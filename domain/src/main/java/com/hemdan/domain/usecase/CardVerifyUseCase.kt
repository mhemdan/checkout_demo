package com.hemdan.domain.usecase

import com.hemdan.domain.DispatcherProvider
import com.hemdan.domain.FAILURE_CALLBACK_URL
import com.hemdan.domain.SUCCESS_CALLBACK_URL
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Card verify use case
 *
 * @property dispatcherProvider
 * @constructor Create Card verify use case
 */
class CardVerifyUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) {
    /**
     * Invoke
     *
     * @param url
     * @return CardVerifyResult with success or error
     */
    suspend operator fun invoke(url: String): CardVerifyResult {
        return withContext(dispatcherProvider.io()) {
            val isSubmitted =
                if (url.contains(SUCCESS_CALLBACK_URL)) {
                    true
                } else if (url.contains(
                        FAILURE_CALLBACK_URL
                    )
                ) {
                    false
                } else {
                    null
                }
            CardVerifyResult.SubmitState(isSubmitted)
        }
    }

    /**
     * Card verify result
     *
     * @constructor Create Card verify result
     */
    sealed class CardVerifyResult {
        /**
         * Submit state
         *
         * @property isSubmitted
         * @constructor Create Submit state
         */
        data class SubmitState(val isSubmitted: Boolean? = null) : CardVerifyResult()
    }
}
