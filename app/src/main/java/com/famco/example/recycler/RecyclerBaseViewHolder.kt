package com.famco.example.base.recycler

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class RecyclerBaseViewHolder( binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    private var binding: ViewDataBinding = binding




    fun bind(obj: Any?) {

        binding.setVariable(com.famco.example.BR.vm, obj)
        binding.executePendingBindings()

    }
}