package com.medina.pokertinker.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.medina.pokertinker.Data.model.User
import com.medina.pokertinker.Util.SharedPreferenceUtil

class LoginViewModel(private val context: Context): ViewModel() {
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    val emptyFieldsError = MutableLiveData<Boolean>()
    val fieldsAuthenticateError = MutableLiveData<Boolean>()
    val goSuccessActivity = MutableLiveData<Boolean>()

    fun OnCreate(){
        sharedPreferenceUtil=SharedPreferenceUtil().also {
            it.setSharedPreference(context)
        }
    }

    fun validateInputs(email:String, password:String){
        if (email.isEmpty() && password.isEmpty()){
            emptyFieldsError.postValue(true)
        }
        val user: User?=sharedPreferenceUtil.getUser()
        if (email.equals(user?.email) && password.equals(user?.password)){
            goSuccessActivity.postValue(true)
        } else{
            fieldsAuthenticateError.postValue(true)
        }
    }
}