package com.alfanshter.ikidesa.menu.home.ui.kuliner

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alfanshter.ikidesa.R

class KulinerFragment : Fragment() {

    companion object {
        fun newInstance() = KulinerFragment()
    }

    private lateinit var viewModel: KulinerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.kuliner_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(KulinerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}