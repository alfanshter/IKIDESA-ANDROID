package com.alfanshter.ikidesa.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VersiModel {
    @SerializedName("nama_aplikasi")
    @Expose
    var nama_aplikasi : String? = null

    @SerializedName("versi_aplikasi")
    @Expose
    var versi_aplikasi : String? = null

    @SerializedName("link_download")
    @Expose
    var link_download : String? = null

}