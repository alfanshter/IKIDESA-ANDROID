package com.alfanshter.udinlelangfix.Session

import android.content.Context
import android.content.SharedPreferences

class SessionManager(private val context: Context?) {
    val privateMode = 0
    val privateName ="login"
    var Pref : SharedPreferences?=context?.getSharedPreferences(privateName,privateMode)
    var editor : SharedPreferences.Editor?=Pref?.edit()

    private val islogin = "login"
    fun setLogin(check: Boolean){
        editor?.putBoolean(islogin,check)
        editor?.commit()
    }

    fun getLogin():Boolean?
    {
        return Pref?.getBoolean(islogin,false)
    }

    private val islogintelfon = "logintelfon"
    fun setLogintelfon(check: Boolean){
        editor?.putBoolean(islogintelfon,check)
        editor?.commit()
    }

    fun getLogintelfon():Boolean?
    {
        return Pref?.getBoolean(islogintelfon,false)
    }


    private val iduser = "iduser"
    fun setiduser(check:String)
    {
        editor?.putString(iduser,check)
        editor?.commit()
    }

    fun getiduser():String?
    {
        return Pref?.getString(iduser,"")
    }

    private val logicwarung = "logicwarung"
    fun setlogicwarung(check:String)
    {
        editor?.putString(logicwarung,check)
        editor?.commit()
    }

    fun getlogicwarung():String?
    {
        return Pref?.getString(logicwarung,"")
    }



    private val telefon = "telefon"
    fun settelefon(check:String)
    {
        editor?.putString(telefon,check)
        editor?.commit()
    }

    fun gettelefon():String?
    {
        return Pref?.getString(telefon,"")
    }


    private val nama = "nama"
    fun setnama(check:String?)
    {
        editor?.putString(nama,check)
        editor?.commit()
    }

    fun getnama():String?
    {
        return Pref?.getString(nama,"")
    }



}