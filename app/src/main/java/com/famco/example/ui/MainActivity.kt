package com.famco.example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.famco.example.R
import com.famco.example.databinding.ActivityMainBinding
import com.famco.example.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var vm:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


         vm= MainViewModel(this)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm=vm
        binding.executePendingBindings()

    }
}