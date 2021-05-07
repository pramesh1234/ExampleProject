package com.famco.example.utils

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.util.Pair
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.famco.example.R
import com.google.android.material.textfield.TextInputLayout

import com.squareup.picasso.Picasso

private const val TAG = "BindingUtils"
@BindingAdapter("app:setError")
fun setError(view: TextInputLayout, text: BindableString) {
    view.error = text.get()
}
@BindingAdapter("app:binding")
fun bindEditText(view: EditText, bindableString: BindableString) {
    val pair: Pair<BindableString, TextWatcherAdapter>? =
        view.getTag(R.id.bound_observable) as Pair<BindableString, TextWatcherAdapter>?
    if (pair == null || pair.first !== bindableString) {
        if (pair != null) {
            view.removeTextChangedListener(pair.second)
        }
        val watcher: TextWatcherAdapter = object : TextWatcherAdapter() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                bindableString.set(s.toString())
                Log.e("BindingUtils", "onTextChanged: " + s.toString())
            }
        }
        view.setTag(R.id.bound_observable, Pair<Any, Any>(bindableString, watcher))
        view.addTextChangedListener(watcher)
    }
    val newValue = bindableString.get()
    if (view.text.toString() != newValue) {
        view.setText(newValue)
    }
}
@BindingAdapter("setImageUrl")
fun ImageView.bindImageUrl(url: String?) {
    if (url != null && url.isNotBlank()) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.slide_placeholder)
            .into(this)
    }
}

@BindingAdapter("app:bindVisibility")
fun bindVisibility(view: View, bindableBoolean: Boolean) {
    if (bindableBoolean) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}
@BindingAdapter("setRecyclerViewAdapter")
fun setRecyclerViewAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
    view.setHasFixedSize(true)
    val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(view.context)
    view.layoutManager = mLayoutManager
    view.adapter = adapter
}
@BindingAdapter("setHorizontalRecyclerViewAdapter")
fun setHorizontalRecyclerViewAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
    view.setHasFixedSize(true)
    val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
    view.layoutManager = mLayoutManager
    view.adapter = adapter
}
object BindingUtils {


}