package com.gmiller.shoppinglist_learnapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import com.gmiller.shoppinglist_learnapp.R
import com.gmiller.shoppinglist_learnapp.databinding.ActivityMainBinding
import com.gmiller.shoppinglist_learnapp.domain.ShopItem

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var llShopList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        llShopList = binding.llShopList
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){
            showList(it)
        }
    }
    private fun showList(list: List<ShopItem>) {
        llShopList.removeAllViews()
        for (shopItem in list){
            val layoutId = if(shopItem.enabled){
                R.layout.item_shop_enabled
            } else {
                R.layout.item_shop_disabled
            }
            val view = LayoutInflater
                .from(this).inflate(layoutId, llShopList, false)
            view.setOnLongClickListener {
                viewModel.changeEnableState(shopItem)
                true
            }
            llShopList.addView(view)
        }
    }
}