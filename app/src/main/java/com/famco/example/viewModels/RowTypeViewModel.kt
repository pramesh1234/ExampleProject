package com.famco.example.viewModels

import android.view.View
import com.famco.example.ui.MainActivity
import com.famco.example.utils.BindableString

class RowTypeViewModel(val mainActivity: MainActivity) {
    val typeName = BindableString()
    val typeImage = BindableString()
    fun onClickType(v:View){
            mainActivity.vm.setType(typeName.get())
    }
}