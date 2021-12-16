package com.alfanshter.ikidesa.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseVersi {
    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: VersiModel? = null
}