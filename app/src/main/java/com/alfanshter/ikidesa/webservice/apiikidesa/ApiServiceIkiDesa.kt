package com.alfanshter.ikidesa.webservice.apiikidesa

import com.alfanshter.ikidesa.model.ResponseVersi
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceIkiDesa {
    @GET("api/versi")
    fun cekversi(@Query("nama_aplikasi") nama_aplikasi: String): Call<ResponseVersi>


}