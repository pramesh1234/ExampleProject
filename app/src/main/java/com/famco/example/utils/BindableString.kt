package com.famco.example.utils
import androidx.databinding.BaseObservable
import java.util.*

class BindableString : BaseObservable() {
    var value: String? = null

    fun get(): String {
        return value ?: ""
    }

    fun set(value: String) {
        if (!Objects.equals(this.value, value)) {
            this.value = value
            notifyChange()
        }
    }

    fun isEmpty(): Boolean {
        return value == null || value!!.isEmpty()
    }
}