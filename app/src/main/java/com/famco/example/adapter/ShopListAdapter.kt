package com.famco.example.adapter

import com.famco.example.R
import com.famco.example.recycler.RecyclerBaseAdapter
import com.famco.example.viewModels.MainViewModel
import com.famco.example.viewModels.RowItemViewModel

class ShopListAdapter(
    private val shopList: ArrayList<RowItemViewModel>,
    val mainViewModel: MainViewModel
) : RecyclerBaseAdapter() {

    override fun getObjForPosition(position: Int): Any {
        return shopList[position]
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.row_items
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    fun addAll(shopList: ArrayList<RowItemViewModel>) {
        this.shopList.addAll(shopList)
        notifyDataSetChanged()
    }

    fun clear() {
        val size: Int = shopList.size
        mainViewModel.shopList.clear()
        shopList.clear()
        notifyItemRangeRemoved(0, size)


    }

}