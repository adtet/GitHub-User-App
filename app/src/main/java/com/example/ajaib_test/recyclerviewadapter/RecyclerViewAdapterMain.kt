package com.example.ajaib_test.recyclerviewadapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.ajaib_test.DetailUser
import com.example.ajaib_test.MainActivity
import com.example.ajaib_test.ViewModel.MainViewModel
import com.example.ajaib_test.ViewModel.RecyclerViewAdapterMainViewModel
import com.example.ajaib_test.databinding.ShowUserItemBinding
import com.example.ajaib_test.model.modelItems.responseDataItems
import okhttp3.HttpUrl
import okhttp3.Response
import org.json.JSONObject
import java.lang.reflect.Array.get
import java.nio.file.Paths.get


class RecyclerViewAdapterMain(private val context: Context):RecyclerView.Adapter<RecyclerViewAdapterMain.ViewHolder>() {

    private val datalist = ArrayList<responseDataItems>()
    var viewModel:RecyclerViewAdapterMainViewModel = ViewModelProviders.of((context as MainActivity))[RecyclerViewAdapterMainViewModel::class.java]


    fun setdatalist(items: ArrayList<responseDataItems>){
        datalist.clear()
        datalist.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ShowUserItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(items: responseDataItems){

            binding.apply {
                Glide.with(itemView).load(items.avatar_url).transition(DrawableTransitionOptions.withCrossFade()).centerCrop().into(imageShowUser)
                txtusernameShowUserItem.text = items.login
                txtcompanyShowUserItem.text = items.html_url
                linearlayoutShowItemUser.setOnClickListener {
                    val i = Intent(context,DetailUser::class.java)
                    i.putExtra("login",items.login)
                    context.startActivity(i)
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ShowUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datalist[position])



    }

    override fun getItemCount(): Int {
        return datalist.size
    }




}