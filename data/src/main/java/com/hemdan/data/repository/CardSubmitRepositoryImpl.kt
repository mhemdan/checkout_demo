package com.hemdan.data.repository

import com.hemdan.data.datasource.remote.VerifyCardApi
import com.hemdan.data.mapper.toRequest
import com.hemdan.domain.model.CardInfo
import com.hemdan.domain.repository.CardSubmitRepository
import javax.inject.Inject

class CardSubmitRepositoryImpl @Inject constructor(private val api: VerifyCardApi) : CardSubmitRepository {
    override suspend fun submit(cardInfo: CardInfo): String {
        return api.verify(cardInfo.toRequest()).url
    }
}