package com.alfanshter.ikidesa.splashscreen

import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.alfanshter.ikidesa.BuildConfig
import com.alfanshter.ikidesa.R
import com.alfanshter.ikidesa.auth.login.LoginActivity
import com.alfanshter.ikidesa.menu.home.HomeActivity
import com.alfanshter.ikidesa.model.ResponseVersi
import com.alfanshter.ikidesa.webservice.apiikidesa.ApiClientIkiDesa
import kotlinx.android.synthetic.main.koneksi.*
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplashScreenActivity : AppCompatActivity(),AnkoLogger {
    lateinit var handler: Handler
    lateinit var dialog: AlertDialog
    private var api = ApiClientIkiDesa.instance()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        checkInternet()
    }


    fun showHome(link : String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Silahkan update dulu aplikasinya ")
        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    val appPackageName =
                        packageName // getPackageName() from Context or Activity object

                    try {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(link)
                            )
                        )
                    } catch (anfe: ActivityNotFoundException) {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(link)
                            )
                        )
                    }
                    toast("muask")
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    showHome(link)
                }
                DialogInterface.BUTTON_NEUTRAL -> {
                }
            }
        }


        // Set the alert dialog positive/yes button
        builder.setPositiveButton("YES", dialogClickListener)

        // Set the alert dialog negative/no button
        builder.setNegativeButton("NO", dialogClickListener)

        // Set the alert dialog neutral/cancel button
        builder.setNeutralButton("CANCEL", dialogClickListener)


        // Initialize the AlertDialog using builder object
        dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    fun checkInternet() {
        val manager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as  ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if (null != networkInfo){
            if (networkInfo.type == ConnectivityManager.TYPE_WIFI){
                getversiaplikasi()

            }else if (networkInfo.type == ConnectivityManager.TYPE_MOBILE){
                    getversiaplikasi()
            }
        }else{
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.koneksi)
            dialog.setCanceledOnTouchOutside(false)
            dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)

            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.btn_cobalagi.setOnClickListener {
                    recreate()
            }
            dialog.show()
        }
    }

    fun getversiaplikasi(){
        api.cekversi("ikidesa").enqueue(object : Callback<ResponseVersi>{
            override fun onResponse(call: Call<ResponseVersi>, response: Response<ResponseVersi>) {
                if (response.isSuccessful){
                    //ambil data versi android
                    val versiaplikasi = BuildConfig.VERSION_NAME.toFloat()
                    val versiapi = response.body()!!.data!!.versi_aplikasi!!.toFloat()
                    val linkaplikasi = response.body()!!.data!!.link_download.toString()
                    if (versiapi <=versiaplikasi){
                        handler = Handler()
                        handler.postDelayed({
                            startActivity(intentFor<LoginActivity>().clearTask().newTask())
                            finish()
                        }, 3000)
                    }else{
                        showHome(linkaplikasi)
                    }

                }
            }

            override fun onFailure(call: Call<ResponseVersi>, t: Throwable) {
                info { "ikidesa ${t.message}" }
            }

        })
    }
    override fun onRestart() {
        super.onRestart()

    }
}