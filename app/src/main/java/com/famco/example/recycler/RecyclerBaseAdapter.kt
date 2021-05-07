package com.famco.example.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.famco.example.base.recycler.RecyclerBaseViewHolder

private const val TAG = "RecyclerBaseAdapter"

abstract class RecyclerBaseAdapter : RecyclerView.Adapter<RecyclerBaseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerBaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, viewType, parent, false
        )
        return RecyclerBaseViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: RecyclerBaseViewHolder,
        position: Int
    ) {
        val obj = getObjForPosition(position)
        holder.bind(obj)
        holder.itemView.setOnClickListener {
            Log.e(TAG, "onBindViewHolder: $position")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    protected abstract fun getObjForPosition(position: Int): Any
    protected abstract fun getLayoutIdForPosition(position: Int): Int
}