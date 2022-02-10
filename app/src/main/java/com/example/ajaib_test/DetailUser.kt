package com.example.ajaib_test

import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import android.util.DisplayMetrics
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.ajaib_test.api.RetrofitClient
import com.example.ajaib_test.model.reponseGetDetailUser
import com.example.ajaib_test.model.responseGetUserRepos
import com.example.ajaib_test.recyclerviewadapter.RecyclerViewAdapterDetailUserRepos
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


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

    public lateinit var recyclerView: RecyclerView
    private var avatar_url:String? = null


    private var namelist:ArrayList<String> = ArrayList()
    private var descriptionList:ArrayList<String> = ArrayList()
    private var starlist:ArrayList<String> =  ArrayList()
    private var updatelist:ArrayList<String> =  ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        var displayMetrics: DisplayMetrics = resources.displayMetrics
        dptinggi = displayMetrics.heightPixels
        dplebar = displayMetrics.widthPixels

        linearLayout = findViewById(R.id.linearlayout_show_detail_user)
        val linearLayoutparam = linearLayout.layoutParams
        linearLayoutparam.height = caltinggi(220F, dptinggi)
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





    fun get_detail_user(login: String){
        RetrofitClient.api.getDetailUser(login).enqueue(
            object : Callback<reponseGetDetailUser> {
                override fun onResponse(
                    call: Call<reponseGetDetailUser>,
                    response: Response<reponseGetDetailUser>
                ) {
                    if (response.isSuccessful) {
                        val items = response.body()
                        avatar_url = items!!.avatar_url
                        Glide.with(this@DetailUser).load(avatar_url).transition(
                            DrawableTransitionOptions.withCrossFade()
                        ).centerCrop().into(circleImageView)
                        txtfullname.text = items.name
                        txtusername.text = items.login
                        txtbio.text = items.bio
                        txtemail.text = items.email
                        txtfollowing.text = items.following.toString()
                        txtfollower.text = items.followers.toString()
                        txtlocation.text = items.location

                        get_repos(login)
                    }
                }

                override fun onFailure(call: Call<reponseGetDetailUser>, t: Throwable) {
                    Log.d("Lost Connection", t.message!!)
                }

            }
        )
    }

    fun caltinggi(value: Float, dp: Int): Int {
        return (dp*(value/designhigh)).toInt()
    }

    fun callebar(value: Float, dp: Int):Int{
        return (dp*(value/designwidth)).toInt()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    fun get_repos(login: String){
        namelist.clear()
        descriptionList.clear()
        starlist.clear()
        updatelist.clear()
        RetrofitClient.api.getListRepos(login).enqueue(
            object : Callback<List<responseGetUserRepos>> {
                override fun onResponse(
                    call: Call<List<responseGetUserRepos>>,
                    response: Response<List<responseGetUserRepos>>
                ) {
                    if (response.isSuccessful) {
                        val items = response.body()
                        if (items!!.isEmpty()) {
                            Toast.makeText(
                                applicationContext,
                                "No Repo For this account",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            for (i in 0 until items.count()) {
                                namelist.add(items[i].name)
                                descriptionList.add(items[i].description)
                                starlist.add(items[i].stargazers_count.toString())
                                updatelist.add(date_conv_to_last_update(items[i].updated_at))
                            }
                            show_data(avatar_url!!,namelist,descriptionList,starlist,updatelist,this@DetailUser)

                        }
                    }
                }

                override fun onFailure(call: Call<List<responseGetUserRepos>>, t: Throwable) {
                    Log.d("Lost Connection", t.message!!)
                }

            }
        )
    }

    fun show_data(
        avatar_url: String,
        namelist: ArrayList<String>,
        descriptionlist: ArrayList<String>,
        starlist: ArrayList<String>,
        lastupdatelist: ArrayList<String>,
        context: Context
    ){
        recyclerView = findViewById(R.id.recycler_detail_user)
        var layoutmanager = LinearLayoutManager(this)
        recyclerView.layoutManager=layoutmanager
        recyclerView.setHasFixedSize(true)
        var adapter = RecyclerViewAdapterDetailUserRepos(
            avatar_url,
            namelist,
            descriptionlist,
            starlist,
            lastupdatelist,
            context
        )
        recyclerView.adapter = adapter

    }

    fun date_conv_to_last_update(dateStr: String):String{
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        inputFormat.timeZone = TimeZone.getTimeZone("GMT")
        val date: Date = inputFormat.parse(dateStr)
        val niceDateStr: String = DateUtils.getRelativeTimeSpanString(
            date.time,
            Calendar.getInstance().timeInMillis,
            DateUtils.MINUTE_IN_MILLIS
        ) as String

        return niceDateStr


    }


}