package com.famco.swaddeshfoodcourt.utils

import android.util.Patterns

object ValidationUtils {
    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPhoneValid(phoneNumber: CharSequence): Boolean {
        return Patterns.PHONE.matcher(phoneNumber).matches()
    }
}