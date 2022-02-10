package com.example.ajaib_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ajaib_test.ViewModel.MainViewModel
import com.example.ajaib_test.databinding.ActivityMainBinding
import com.example.ajaib_test.recyclerviewadapter.RecyclerViewAdapterMain

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel :MainViewModel
    private lateinit var adapter : RecyclerViewAdapterMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        adapter = RecyclerViewAdapterMain(this)
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.apply {
            recyclerMain.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerMain.setHasFixedSize(true)
            recyclerMain.adapter = adapter

            btncarimain.setOnClickListener {
                searchDatauser()
            }
            txtcarimain.setOnKeyListener { v, keyCode, event ->
                if(event.action==KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    searchDatauser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false

            }
        }
        viewModel.getDataSearchUsers().observe(this,{
            if(it!=null){
                adapter.setdatalist(it)
                showLoading(false)
            }
        })
    }


    private fun showLoading(state:Boolean){
        if(state){
            binding.progressMain.visibility = View.VISIBLE
        }
        else{
            binding.progressMain.visibility = View.INVISIBLE
        }
    }
    private fun searchDatauser(){
        binding.apply {
            val query = txtcarimain.text.toString()
            if(query.isEmpty())return
            showLoading(true)
            viewModel.searchUsers(query)
        }
    }
}