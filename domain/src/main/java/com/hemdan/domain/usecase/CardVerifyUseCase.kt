package com.hemdan.domain.usecase

import com.hemdan.domain.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardVerifyUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) {
    suspend operator fun invoke(url: String): CardVerifyResult {
        return withContext(dispatcherProvider.io()) {
            try {
                val isSubmitted =
                    if (url.contains(SUCCESS)) {
                        true
                    } else if (url.contains(
                            FAILURE
                        )
                    ) {
                        false
                    } else {
                        null
                    }
                CardVerifyResult.Success(isSubmitted)
            } catch (e: Exception) {
                CardVerifyResult.Error(e)
            }
        }
    }

    sealed class CardVerifyResult {
        data class Success(val isSubmitted: Boolean? = null) : CardVerifyResult()
        data class Error(val e: Exception) : CardVerifyResult()
    }
}