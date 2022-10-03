package com.hemdan.domain.usecase

import com.hemdan.domain.DispatcherProvider
import com.hemdan.domain.model.CardInfo
import com.hemdan.domain.repository.CardVerifyRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardVerifyUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: CardVerifyRepository
) {
    suspend operator fun invoke(cardInfo: CardInfo): String {
        return withContext(dispatcherProvider.io()) {
            repository.verify(cardInfo)
        }
    }
}