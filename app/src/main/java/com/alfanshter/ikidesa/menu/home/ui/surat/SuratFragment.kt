package com.alfanshter.ikidesa.menu.home.ui.surat

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alfanshter.ikidesa.R
import com.alfanshter.ikidesa.databinding.SuratFragmentBinding
import com.alfanshter.ikidesa.ui.surataktakelahiran.SuratAktaKelahiranActivity
import kotlinx.android.synthetic.main.surat_fragment.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class SuratFragment : Fragment() {

    companion object {
        fun newInstance() = SuratFragment()
    }

    lateinit var binding : SuratFragmentBinding
    private lateinit var viewModel: SuratViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root =  inflater.inflate(R.layout.surat_fragment, container, false)

        root.apply {
            txt_pribadi.setOnClickListener {
                txt_pribadi.backgroundColor = resources.getColor(R.color.biru)
                txt_lainnya.backgroundColor = resources.getColor(R.color.white)
                layout_pribadi.visibility = View.VISIBLE
                layout_lainnya.visibility = View.GONE
            }
            txt_lainnya.setOnClickListener {
                txt_pribadi.backgroundColor = resources.getColor(R.color.white)
                txt_lainnya.backgroundColor = resources.getColor(R.color.biru)
                layout_pribadi.visibility = View.GONE
                layout_lainnya.visibility = View.VISIBLE
            }

            btn_lahirbaru.setOnClickListener {
                startActivity<SuratAktaKelahiranActivity>()
            }
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SuratViewModel::class.java)
        // TODO: Use the ViewModel
    }

}