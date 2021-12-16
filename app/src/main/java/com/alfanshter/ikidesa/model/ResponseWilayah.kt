package com.alfanshter.ikidesa.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WrappedListResponse<T>(
    @SerializedName("provinsi") var provinsi : List<T>? = null
)



data class WrappedResponse<T>(
    @SerializedName("provinsi") var provinsi :T? = null
)

class ResponseWilayah{
    @Expose
    @SerializedName("provinsi")
    var provinsi: List<Provinsi> = ArrayList()

    class Provinsi {
        var id: Int? = null
        var nama: String? = null
        override fun toString(): String {
            return nama!!
        }

    }
}

class ResponseKota{
    @Expose
    @SerializedName("kota_kabupaten")
    var kota: List<Kota> = ArrayList()

    class Kota{
        var id: Int? = null
        var id_provinsi : String? = null
        var nama : String? = null
        override fun toString(): String {
            return nama!!
        }
    }
}

class ResponseKecamatan{
    @Expose
    @SerializedName("kecamatan")
    var kecamatan : List<Kecamatan> = ArrayList()

    class Kecamatan{
        var id : Int?  =null
        var id_kota : String? = null
        var nama : String? = null
        override fun toString(): String {
            return nama!!
        }
    }
}

class ResponseKelurahan{
    @Expose
    @SerializedName("kelurahan")
    var kelurahan : List<Kelurahan> = ArrayList()

    class Kelurahan{
        var id : Double? = null
        var id_kecamatan : String? = null
        var nama : String? = null

        override fun toString(): String {
            return nama!!
        }
    }
}

