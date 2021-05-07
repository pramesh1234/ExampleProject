package com.famco.example.adapter

import com.famco.example.R
import com.famco.example.recycler.RecyclerBaseAdapter
import com.famco.example.viewModels.RowItemViewModel
import com.famco.example.viewModels.RowTypeViewModel

class ShopTypeAdapter(val shopTypeList:ArrayList<RowTypeViewModel>) : RecyclerBaseAdapter() {
    override fun getObjForPosition(position: Int): Any {
       return shopTypeList[position]
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.row_type
    }

    override fun getItemCount(): Int {
      return shopTypeList.size
    }
    fun addAll(shopTypeList: ArrayList<RowTypeViewModel>) {
        this.shopTypeList.addAll(shopTypeList)
        notifyDataSetChanged()
    }
}