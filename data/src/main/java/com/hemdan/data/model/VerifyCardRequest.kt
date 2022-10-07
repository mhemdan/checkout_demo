package com.hemdan.data.model


import com.google.gson.annotations.SerializedName

data class VerifyCardRequest(
    @SerializedName("cvv")
    val cvv: String,
    @SerializedName("expiry_month")
    val expiryMonth: String,
    @SerializedName("expiry_year")
    val expiryYear: String,
    @SerializedName("failure_url")
    val failureUrl: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("success_url")
    val successUrl: String
)