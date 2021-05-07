package com.famco.example.viewModels

import android.util.Log
import android.widget.Toast
import com.famco.example.ui.MainActivity
import com.famco.example.adapter.ShopListAdapter
import com.famco.example.adapter.ShopTypeAdapter
import com.famco.example.api.ApiCall
import com.famco.example.utils.BindableBoolean
import com.famco.example.utils.BindableString
import com.famco.example.utils.Constants
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val TAG = "MainViewModel"

class MainViewModel(val mainActivity: MainActivity) {
    val shopList: ArrayList<RowItemViewModel> = ArrayList()
    val typeList: ArrayList<RowTypeViewModel> = ArrayList()
    val loadingCompleted=BindableBoolean()
    var shopListAdapter:ShopListAdapter = ShopListAdapter(ArrayList<RowItemViewModel>(),this)
    val shopCategoryAdapter = ShopTypeAdapter(ArrayList<RowTypeViewModel>())
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    init {
        shopCategory(retrofit)
        shopsList(retrofit, "all")


    }

    fun shopCategory(retrofit: Retrofit) {
        val API: ApiCall = retrofit.create(ApiCall::class.java)
        val call: Call<JsonObject>? = API.category
        call?.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                val typeObject: JsonObject? = response?.body() as JsonObject
                try {
                    val responseObject: JsonObject? = typeObject?.getAsJsonObject("response")
                    val shopDataArray: JsonArray? = responseObject?.getAsJsonArray("category")
                    if (shopDataArray != null) {
                        val viewModelAll = RowTypeViewModel(mainActivity)
                        viewModelAll.typeName.set("All")
                        viewModelAll.typeImage.set("")
                        typeList.add(viewModelAll)
                        for (i in shopDataArray) {
                            val typeName = i.asJsonObject["name_eng"].asString
                            val typeImage = i.asJsonObject["image"].asString
                            val viewModel = RowTypeViewModel(mainActivity)
                            viewModel.typeName.set(typeName)
                            viewModel.typeImage.set(typeImage)

                            typeList.add(viewModel)
                        }
                        shopCategoryAdapter.addAll(typeList)

                    }
                } catch (e: JSONException) {
                    e.printStackTrace()

                }

            }

            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                Log.e(TAG, "onFailure: ${t.toString()}")
            }

        })

    }

    fun shopsList(retrofit: Retrofit, type: String) {

        val API: ApiCall = retrofit.create(ApiCall::class.java)
        val call: Call<JsonObject>? = API.getShop(type, "3099", "21.1956942", "72.808376")
        Log.e(TAG, "shops: ${call.toString()}")
        call?.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                val shopsObject: JsonObject? = response?.body() as JsonObject
                try {
                    val responseObject: JsonObject? = shopsObject?.getAsJsonObject("response")
                    val shopDataArray: JsonArray? = responseObject?.getAsJsonArray("shop_data")
                    if (shopDataArray != null) {
                        if (shopDataArray.size() == 0) {
                            Toast.makeText(mainActivity, "No shops in this category", Toast.LENGTH_SHORT).show()
                        }
                        for (i in shopDataArray) {
                            val shopName = i.asJsonObject["shop_name"].asString
                            val shopType = i.asJsonObject["shop_type"].asString
                            val shopTiming = i.asJsonObject["shop_timing"].asString
                            val shopPicture = i.asJsonObject["shop_picture"].asString
                            val viewModel = RowItemViewModel()
                            viewModel.shopName.set(shopName)
                            viewModel.shopImage.set(shopPicture)
                            viewModel.shopTiming.set(shopTiming)
                            viewModel.shopType.set(shopType)
                            Log.e(TAG, "onResponse: ${viewModel.shopType.get()}")
                            shopList.add(viewModel)
                        }
                        shopListAdapter.addAll(shopList)
                        loadingCompleted.set(true)

                    } 
                } catch (e: JSONException) {
                    e.printStackTrace()

                }
            }

            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                Log.e(TAG, "onFailure: ${t.toString()}")
            }

        })

    }

    fun setType(type: String) {
        loadingCompleted.set(false)
        shopListAdapter.clear()
        shopsList(retrofit, type)
    }

}