package com.alfanshter.ikidesa.webservice.apiwilayah

import com.alfanshter.ikidesa.model.ResponseKecamatan
import com.alfanshter.ikidesa.model.ResponseKelurahan
import com.alfanshter.ikidesa.model.ResponseKota
import com.alfanshter.ikidesa.model.ResponseWilayah
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("provinsi")
    fun getprovinsi() : Call<ResponseWilayah>

    @GET("kota")
    fun getkota(
        @Query("id_provinsi") parameter : String
    ):Call<ResponseKota>

    @GET("kecamatan")
    fun getkecamatan(
        @Query("id_kota") parameter: String
    ):Call<ResponseKecamatan>

    @GET("kelurahan")
    fun getkelurahan(
        @Query("id_kecamatan") parameter: String
    ):Call<ResponseKelurahan>

}