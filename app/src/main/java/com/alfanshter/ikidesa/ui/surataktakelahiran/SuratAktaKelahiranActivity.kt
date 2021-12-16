package com.alfanshter.ikidesa.ui.surataktakelahiran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alfanshter.ikidesa.R
import kotlinx.android.synthetic.main.activity_surat_akta_kelahiran.*

class SuratAktaKelahiranActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surat_akta_kelahiran)

        btn_back.setOnClickListener {
            finish()
        }
    }
}