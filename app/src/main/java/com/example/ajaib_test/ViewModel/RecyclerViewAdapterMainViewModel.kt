package com.example.ajaib_test.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ajaib_test.api.RetrofitClient
import com.example.ajaib_test.model.reponseGetDetailUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewAdapterMainViewModel:ViewModel() {
    val Name = MutableLiveData<String>()
    val Company = MutableLiveData<String>()


    fun get_detail(path:String){
        RetrofitClient.api.getDetailUser(path).enqueue(
                object : Callback<reponseGetDetailUser> {
                    override fun onResponse(call: Call<reponseGetDetailUser>, response: Response<reponseGetDetailUser>) {
                        if(response.isSuccessful){
                            Name.postValue(response.body()!!.name)
                            Company.postValue(response.body()!!.company)
                        }
                    }

                    override fun onFailure(call: Call<reponseGetDetailUser>, t: Throwable) {
                        Log.d("Lost Connection", t.message!!)
                    }

                }
        )
    }

    fun getName():LiveData<String>{
        return Name
    }
    fun getCompany():LiveData<String>{
        return Company
    }
}