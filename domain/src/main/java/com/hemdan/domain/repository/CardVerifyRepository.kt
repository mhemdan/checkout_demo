package com.hemdan.domain.repository

import com.hemdan.domain.model.CardInfo

interface CardVerifyRepository {
    suspend fun verify(cardInfo: CardInfo) : String
}