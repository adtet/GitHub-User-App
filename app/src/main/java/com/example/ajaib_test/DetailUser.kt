package com.example.ajaib_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ajaib_test.api.RetrofitClient
import com.example.ajaib_test.model.reponseGetDetailUser
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUser : AppCompatActivity() {

    private lateinit var  txtusername:TextView
    private lateinit var  circleImageView:CircleImageView
    private lateinit var  linearLayout: LinearLayout
    private lateinit var  txtbio:TextView
    private lateinit var txtfollowing:TextView
    private lateinit var txtfollower:TextView
    private lateinit var txtlocation:TextView
    private lateinit var txtemail:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        txtusername = findViewById(R.id.txtusername_detail_user)

    }



    fun get_detail_user(login:String){
        RetrofitClient.api.getDetailUser(login).enqueue(
                object : Callback<reponseGetDetailUser>{
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


}