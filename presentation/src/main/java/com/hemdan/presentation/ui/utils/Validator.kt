package com.hemdan.presentation.ui.utils

import javax.inject.Inject

class Validator @Inject constructor() {
    fun isValidCardNumber(cardNumber: String): Boolean {

        if(getCardType(cardNumber) == null)
            return false

        var s1 = 0
        var s2 = 0
        val reverse = StringBuffer(cardNumber).reverse().toString()
        for (i in reverse.indices) {
            val digit = Character.digit(reverse[i], 10)
            when {
                i % 2 == 0 -> s1 += digit
                else -> {
                    s2 += 2 * digit
                    when {
                        digit >= 5 -> s2 -= 9
                    }
                }
            }
        }
        return (s1 + s2) % 10 == 0
    }

    fun getCardType(cardNumber: String): Card? {
        return cardTypes.firstOrNull { it.regex.matches(cardNumber) }
    }
}

private val cardTypes = listOf(Visa(), AMEX(), MasterCard())

sealed class Card {
    abstract val regex: Regex
    abstract val mask: String
    abstract val schemaIconId: Int
    abstract val cvvLength: Int
    abstract val digitCount: Int
}

data class Visa(
    override val regex: Regex = "^4[0-9]{0,}\$".toRegex(),
    override val mask: String = "#### #### #### ####",
    override val schemaIconId: Int = 0,
    override val cvvLength: Int = 3,
    override val digitCount: Int = 16
) : Card()

data class MasterCard(
    override val regex: Regex = "^(5[1-5]|222[1-9]|22[3-9]|2[3-6]|27[01]|2720)[0-9]{0,}\$".toRegex(),
    override val mask: String = "#### #### #### ####",
    override val schemaIconId: Int = 0,
    override val cvvLength: Int = 3,
    override val digitCount: Int = 16
) : Card()

data class AMEX(
    override val regex: Regex = "^3[47][0-9]{0,}\$".toRegex(),
    override val mask: String = "#### ###### #####",
    override val schemaIconId: Int = 0,
    override val cvvLength: Int = 4,
    override val digitCount: Int = 15
) : Card()
