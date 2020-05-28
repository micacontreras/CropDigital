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
    private val _onSuccess = MutableLiveData<ItemsResponse>()

    val listItemsResponse: LiveData<List<ItemsResponse>>
        get() = _listItems

    val onError: LiveData<Boolean>
        get() = _onError

    val onSuccess: LiveData<ItemsResponse>
        get() = _onSuccess

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

    fun getItemSelected(index:Int){
        viewModelScope.launch {
            val getItemDeferred = RepositoryApi.retrofitService.getIndex(index)
            try {
                getItemDeferred.await().let {
                    _onSuccess.value = it
                }
            } catch (e: Exception) {
                _onError.value = true
            }

        }
    }
}