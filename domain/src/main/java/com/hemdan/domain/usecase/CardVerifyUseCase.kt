package com.hemdan.domain.usecase

import com.hemdan.domain.DispatcherProvider
import com.hemdan.domain.FAILURE_CALLBACK_URL
import com.hemdan.domain.SUCCESS_CALLBACK_URL
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardVerifyUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) {
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

    sealed class CardVerifyResult {
        data class SubmitState(val isSubmitted: Boolean? = null) : CardVerifyResult()
    }
}