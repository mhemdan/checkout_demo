package com.hemdan.domain.repository

import com.hemdan.domain.model.CardInfo

interface VerifyCardRepository {
    suspend fun verify(cardInfo: CardInfo) : String
}