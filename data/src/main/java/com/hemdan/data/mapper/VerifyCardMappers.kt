package com.hemdan.data.mapper

import com.hemdan.data.model.VerifyCardRequest
import com.hemdan.domain.FAILURE_CALLBACK_URL
import com.hemdan.domain.SUCCESS_CALLBACK_URL
import com.hemdan.domain.model.CardInfo

fun CardInfo.toRequest(): VerifyCardRequest = VerifyCardRequest(
    cvv = cvv,
    expiryMonth = expiryMonth,
    expiryYear = expiryYear,
    number = cardNumber,
    successUrl = SUCCESS_CALLBACK_URL,
    failureUrl = FAILURE_CALLBACK_URL
)