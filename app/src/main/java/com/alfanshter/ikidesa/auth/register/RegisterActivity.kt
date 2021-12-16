package com.alfanshter.ikidesa.auth.register

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alfanshter.ikidesa.webservice.apiwilayah.ApiWilayahClient
import com.alfanshter.ikidesa.R
import com.alfanshter.ikidesa.model.*
import com.alfanshter.ikidesa.utils.CustomProgressDialog
import com.alfanshter.udinlelangfix.Session.SessionManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.AnkoLogger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity(),AnkoLogger {
    private var api = ApiWilayahClient.instance()

    lateinit var auth: FirebaseAuth
    private var progressdialog = CustomProgressDialog()
    lateinit var sessionManager: SessionManager
    //ID API WILAYAH
    var idprovinsi: Int? = null
    var idkota: Int? = null
    var idkecamatan: Int? = null
    var idkelurahan: Int? = null

    companion object {
        var namaprovinsi: String? = null
        var namakota: String? = null
        var namakecamatan: String? = null
        var namakelurahan: String? = null
        var no_telpon: String? = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        sessionManager = SessionManager(this)
        auth = FirebaseAuth.getInstance()

        getprovinsi()
        btn_lanjut.setOnClickListener {
            progressdialog.show(this, "Loading ...")
            progressdialog.dialog.setCanceledOnTouchOutside(false)
            createuser(it)
        }
    }

    fun getprovinsi() {
        api.getprovinsi().enqueue(object : Callback<ResponseWilayah> {
            override fun onFailure(call: Call<ResponseWilayah>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<ResponseWilayah>,
                response: Response<ResponseWilayah>
            ) {
                if (response.isSuccessful) {
                    val provinsi = ResponseWilayah.Provinsi()
                    provinsi.nama = "Pilih Provinsi"
                    provinsi.id = -1
                    var spResponse: MutableList<ResponseWilayah.Provinsi> =
                        response.body()!!.provinsi as MutableList<ResponseWilayah.Provinsi>
                    spResponse.add(0, provinsi)
                    val adapter: ArrayAdapter<ResponseWilayah.Provinsi> =
                        ArrayAdapter<ResponseWilayah.Provinsi>(
                            this@RegisterActivity,
                            android.R.layout.simple_spinner_item,
                            spResponse
                        )
                    spn_provinsi.adapter = adapter
                    spn_provinsi.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                            }

                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                if (position == 0) {

                                }

                                if (position > 0) {
                                    idprovinsi = spResponse[position].id
                                    namaprovinsi = spResponse[position].nama
                                    getkota()
                                }
                            }

                        }

                }
            }

        })
    }


    fun getkota() {
        if (idprovinsi != null) {
            api.getkota(idprovinsi.toString()).enqueue(object : Callback<ResponseKota> {
                override fun onFailure(call: Call<ResponseKota>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<ResponseKota>,
                    response: Response<ResponseKota>
                ) {
                    if (response.isSuccessful) {
                        val kota = ResponseKota.Kota()
                        kota.nama = "Pilih Kota"
                        kota.id = -1

                        var kotaResponse: MutableList<ResponseKota.Kota> =
                            response.body()!!.kota as MutableList<ResponseKota.Kota>
                        kotaResponse.add(0, kota)
                        val adapter: ArrayAdapter<ResponseKota.Kota> =
                            ArrayAdapter<ResponseKota.Kota>(
                                this@RegisterActivity,
                                android.R.layout.simple_spinner_item,
                                kotaResponse
                            )
                        spn_kabupaten.adapter = adapter
                        spn_kabupaten.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                    TODO("Not yet implemented")
                                }

                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    if (position == 0) {

                                    }
                                    if (position > 0) {
                                        idkota = kotaResponse[position].id
                                        namakota = kotaResponse[position].nama
                                        getkecamatan()
                                    }
                                }
                            }

                    }
                }

            })
        }
    }

    fun getkecamatan() {
        api.getkecamatan(idkota.toString()).enqueue(object : Callback<ResponseKecamatan> {
            override fun onFailure(call: Call<ResponseKecamatan>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponseKecamatan>,
                response: Response<ResponseKecamatan>
            ) {
                if (response.isSuccessful) {
                    val kecamatan = ResponseKecamatan.Kecamatan()
                    kecamatan.nama = "Pilih Kecamatan"
                    kecamatan.id = -1
                    var kecamatanresponse: MutableList<ResponseKecamatan.Kecamatan> =
                        response.body()!!.kecamatan as MutableList<ResponseKecamatan.Kecamatan>
                    kecamatanresponse.add(0, kecamatan)
                    val adapter: ArrayAdapter<ResponseKecamatan.Kecamatan> =
                        ArrayAdapter<ResponseKecamatan.Kecamatan>(
                            this@RegisterActivity,
                            android.R.layout.simple_spinner_item,
                            kecamatanresponse
                        )
                    spn_kecamatan.adapter = adapter
                    spn_kecamatan.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {

                            }

                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                if (position == 0) {

                                }
                                if (position > 0) {
                                    idkecamatan = kecamatanresponse[position].id
                                    namakecamatan = kecamatanresponse[position].nama
                                    getkelurahan()
                                }
                            }
                        }

                }
            }

        })
    }

    fun getkelurahan() {
        api.getkelurahan(idkecamatan.toString()).enqueue(object : Callback<ResponseKelurahan> {
            override fun onFailure(call: Call<ResponseKelurahan>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponseKelurahan>,
                response: Response<ResponseKelurahan>
            ) {
                if (response.isSuccessful) {
                    val kelurahan = ResponseKelurahan.Kelurahan()
                    kelurahan.id = -1.12
                    kelurahan.nama = "Pilih Kelurahan"
                    var kelurahanResponse: MutableList<ResponseKelurahan.Kelurahan> =
                        response.body()!!.kelurahan as MutableList<ResponseKelurahan.Kelurahan>
                    kelurahanResponse.add(0, kelurahan)
                    val adapter: ArrayAdapter<ResponseKelurahan.Kelurahan> =
                        ArrayAdapter<ResponseKelurahan.Kelurahan>(
                            this@RegisterActivity,
                            android.R.layout.simple_spinner_item,
                            kelurahanResponse
                        )
                    spn_desa.adapter = adapter
                    spn_desa.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {

                            }

                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                if (position == 0) {

                                }
                                if (position > 1) {
                                    idkelurahan = kelurahanResponse[position].id!!.toInt()
                                    namakelurahan = kelurahanResponse[position].nama

                                }
                            }

                        }
                }
            }

        })
    }

    fun onSNACK(view: View){
        //Snackbar(view)
        val snackbar = Snackbar.make(view, "Isi semua data",
            Snackbar.LENGTH_LONG).setAction("Action", null)
        snackbar.setActionTextColor(Color.BLUE)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.DKGRAY)
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        textView.textSize = 18f
        snackbar.show()
    }


    private fun createuser(v :View){
        val id_users = auth.currentUser!!.uid
        val email = auth.currentUser!!.email.toString()
        val nama = edt_namalengkap.text.toString()
        val nik = edt_nik.text.toString()
        val alamat_lengkap = edt_alamatuser.text.toString()

        if (id_users.isNotEmpty() && email.isNotEmpty() && nama.isNotEmpty() && nik.isNotEmpty() && namaprovinsi!=null && namakota!=null && namakecamatan!=null && namakelurahan!=null
            && alamat_lengkap.isNotEmpty()){
            val user = UsersModel(id_users,email,nama,nik, namaprovinsi, namakota,
                namakecamatan, namakelurahan,alamat_lengkap)
                //tambahkan register
        }else{
            progressdialog.dialog.dismiss()
            onSNACK(v)
        }

    }
}