package com.example.ajaib_test.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ajaib_test.api.RetrofitClient
import com.example.ajaib_test.model.modelItems.responseDataItems
import com.example.ajaib_test.model.reponseGetDetailUser
import com.example.ajaib_test.model.reponseGetSearchUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel:ViewModel() {
    val listUsers = MutableLiveData<ArrayList<responseDataItems>>()
    val namelist:ArrayList<String> = ArrayList()
    val companylist:ArrayList<String> = ArrayList()
    val items:ArrayList<responseDataItems> = ArrayList()
    val combine_data:ArrayList<String> = ArrayList()

    fun searchUsers(query:String){
        RetrofitClient.api.getSearchUsers(query).enqueue(
            object : Callback<reponseGetSearchUser>{
                override fun onResponse(
                    call: Call<reponseGetSearchUser>,
                    response: Response<reponseGetSearchUser>
                ) {
                    if(response.isSuccessful){
//                        items.addAll(response.body()!!.items)
                        listUsers.postValue(response.body()!!.items)

                    }
                }

                override fun onFailure(call: Call<reponseGetSearchUser>, t: Throwable) {
                   Log.d("Lost Connection", t.message!!)
                }

            }
        )
    }

    fun get_detail(path:String,items:ArrayList<responseDataItems>){
        RetrofitClient.api.getDetailUser(path).enqueue(
                object : Callback<reponseGetDetailUser> {
                    override fun onResponse(call: Call<reponseGetDetailUser>, response: Response<reponseGetDetailUser>) {
                        if(response.isSuccessful){

                        }
                    }

                    override fun onFailure(call: Call<reponseGetDetailUser>, t: Throwable) {
                        Log.d("Lost Connection", t.message!!)
                    }

                }
        )
    }



    fun getDataSearchUsers():LiveData<ArrayList<responseDataItems>>{
        return listUsers
    }
}