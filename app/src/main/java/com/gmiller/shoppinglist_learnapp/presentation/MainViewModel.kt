package com.gmiller.shoppinglist_learnapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmiller.shoppinglist_learnapp.data.ShopListRepositoryImpl
import com.gmiller.shoppinglist_learnapp.domain.DeleteShopItemUseCase
import com.gmiller.shoppinglist_learnapp.domain.EditShopItemUseCase
import com.gmiller.shoppinglist_learnapp.domain.GetShopListUseCase
import com.gmiller.shoppinglist_learnapp.domain.ShopItem

class MainViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()


    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}