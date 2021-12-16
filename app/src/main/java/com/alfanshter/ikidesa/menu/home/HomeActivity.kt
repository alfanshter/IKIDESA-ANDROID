package com.alfanshter.ikidesa.menu.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alfanshter.ikidesa.R
import com.alfanshter.ikidesa.menu.home.ui.kuliner.KulinerFragment
import com.alfanshter.ikidesa.menu.home.ui.profil.ProfilFragment
import com.alfanshter.ikidesa.menu.home.ui.surat.SuratFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {


    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_surat -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.framehome,
                        SuratFragment()
                    ).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_kuliner -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.framehome,
                        KulinerFragment()
                    ).commit()
                    return@OnNavigationItemSelectedListener true

                }
                R.id.navigation_profil -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.framehome,
                        ProfilFragment()
                    ).commit()
                    return@OnNavigationItemSelectedListener true

                }


            }

            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navView: BottomNavigationView = findViewById(R.id.nav_viewhome)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        moveToFragment(SuratFragment())
    }

    private fun moveToFragment(fragment: Fragment) {
        val fragmentTrans = supportFragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.framehome, fragment)
        fragmentTrans.commit()
    }
}