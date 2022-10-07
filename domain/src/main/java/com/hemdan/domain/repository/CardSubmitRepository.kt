package com.hemdan.domain.repository

import com.hemdan.domain.model.CardInfo

interface CardSubmitRepository {
    suspend fun submit(cardInfo: CardInfo) : String
}