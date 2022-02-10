package com.example.ajaib_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.ajaib_test.api.RetrofitClient
import com.example.ajaib_test.model.reponseGetDetailUser
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUser : AppCompatActivity() {

    public var designwidth = 411
    public var designhigh = 731
    public  var dptinggi : Int = 0
    public var dplebar : Int = 0

    private lateinit var  txtusername:TextView
    private lateinit var txtfullname:TextView
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
        var displayMetrics: DisplayMetrics = resources.displayMetrics
        dptinggi = displayMetrics.heightPixels
        dplebar = displayMetrics.widthPixels

        linearLayout = findViewById(R.id.linearlayout_show_detail_user)
        val linearLayoutparam = linearLayout.layoutParams
        linearLayoutparam.height = caltinggi(220F,dptinggi)
        var login = intent.getStringExtra("login").toString()
        txtusername = findViewById(R.id.txtusername_detail_user)
        txtfullname = findViewById(R.id.txtfullname_detail_user)
        txtbio = findViewById(R.id.txtbio_detail_user)
        txtfollower = findViewById(R.id.txtfollower_detail_user)
        txtfollowing = findViewById(R.id.txtfolloweing_detail_user)
        txtlocation = findViewById(R.id.txtlocation_detail_user)
        txtemail = findViewById(R.id.txtemail_detail_user)
        circleImageView = findViewById(R.id.showimage_detail_user)


        get_detail_user(login)





    }





    fun get_detail_user(login:String){
        RetrofitClient.api.getDetailUser(login).enqueue(
                object : Callback<reponseGetDetailUser>{
                    override fun onResponse(call: Call<reponseGetDetailUser>, response: Response<reponseGetDetailUser>) {
                        if(response.isSuccessful){
                            val items = response.body()
                            Glide.with(this@DetailUser).load(items!!.avatar_url).transition(
                                DrawableTransitionOptions.withCrossFade()).centerCrop().into(circleImageView)
                            txtfullname.text = items.name
                            txtusername.text = items.login
                            txtbio.text = items.bio
                            txtemail.text = items.email
                            txtfollowing.text = items.following.toString()
                            txtfollower.text = items.followers.toString()
                        }
                    }

                    override fun onFailure(call: Call<reponseGetDetailUser>, t: Throwable) {
                        Log.d("Lost Connection", t.message!!)
                    }

                }
        )
    }

    fun caltinggi(value:Float,dp : Int): Int {
        return (dp*(value/designhigh)).toInt()
    }

    fun callebar(value: Float,dp: Int):Int{
        return (dp*(value/designwidth)).toInt()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}