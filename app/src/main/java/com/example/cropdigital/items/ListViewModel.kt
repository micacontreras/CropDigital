package com.example.cropdigital.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cropdigital.network.ItemsResponse
import com.example.cropdigital.network.RepositoryApi
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {

    private val _listItems = MutableLiveData<List<ItemsResponse>>()
    private val _onError = MutableLiveData<Boolean>()

    val listItemsResponse: LiveData<List<ItemsResponse>>
        get() = _listItems

    val onError: LiveData<Boolean>
        get() = _onError

    fun getListItems() {
        viewModelScope.launch {
            val getPropertiesDeferred = RepositoryApi.retrofitService.getItems()
            try {
                val listResult = getPropertiesDeferred.await()
                if (listResult.isNotEmpty()) {
                    _listItems.value = listResult
                }
            } catch (e: Exception) {
                _onError.value = true
            }

        }
    }
}