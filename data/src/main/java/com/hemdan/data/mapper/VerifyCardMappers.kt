package com.hemdan.data.mapper

import com.hemdan.data.model.VerifyCardRequest
import com.hemdan.domain.model.CardInfo

fun CardInfo.toRequest(): VerifyCardRequest = VerifyCardRequest(
    cvv = cvv,
    expiryMonth = expiryMonth,
    expiryYear = expiryYear,
    number = cardNumber,
    failureUrl = "https://success.com",
    successUrl = "https://failure.com"
)