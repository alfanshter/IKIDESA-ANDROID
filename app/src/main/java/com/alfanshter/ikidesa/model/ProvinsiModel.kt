package com.alfanshter.ikidesa.model

data class ProvinsiModel(
    val provinsi : List<DataProvinsi>
)

data class DataProvinsi(
    val id : Int?,
    val nama : String?
)