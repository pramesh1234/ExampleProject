package com.famco.example.utils

import androidx.databinding.BaseObservable

class BindableBoolean : BaseObservable() {
    private var mValue = false
    fun set(mValue : Boolean){
        this.mValue=mValue
        notifyChange()
    }
     fun get():Boolean{
         return mValue
     }
}