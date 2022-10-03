package com.hemdan.data.repository

import com.hemdan.data.datasource.remote.VerifyCardApi
import com.hemdan.data.mapper.toRequest
import com.hemdan.domain.model.CardInfo
import com.hemdan.domain.repository.VerifyCardRepository
import javax.inject.Inject

class VerifyCardRepositoryImpl @Inject constructor(private val api: VerifyCardApi) : VerifyCardRepository {
    override suspend fun verify(cardInfo: CardInfo): String {
        return api.verify(cardInfo.toRequest()).url
    }
}