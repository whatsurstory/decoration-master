package com.beva.itemdecorationsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Date

class CardViewModel: ViewModel() {

    private val _item = MutableLiveData<List<Card>>()
    val item: LiveData<List<Card>>
    get() = _item

    init {
        getItems()
    }

    private fun getItems(): List<Card> {
        val list = mutableListOf<Card>()
        for (i in 1.. 20) {
            list.add(Card(
                image = R.drawable.photo_1481349518771_20055b2a7b24 ,
                title = "Title $i",
                description = "Number $i's description"
                ))
        }
        _item.value = list
        return _item.value!!
    }

}