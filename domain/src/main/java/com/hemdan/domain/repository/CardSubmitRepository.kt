package com.hemdan.domain.repository

import com.hemdan.domain.model.CardInfo

/**
 * Card submit repository
 *
 * @constructor Create empty Card submit repository
 */
interface CardSubmitRepository {
    /**
     * Submit
     *
     * @param cardInfo
     * @return url to do the 3d security check with
     */
    suspend fun submit(cardInfo: CardInfo): String
}
