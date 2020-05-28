package com.example.cropdigital.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cropdigital.network.ItemsRequest
import com.example.cropdigital.network.RepositoryApi
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {

    private val _onSuccess = MutableLiveData<Boolean>()
    private val _onError = MutableLiveData<Boolean>()

    val onSuccess: LiveData<Boolean>
        get() = _onSuccess
    val onError: LiveData<Boolean>
        get() = _onError

    fun addItem(itemsRequest: ItemsRequest) {
        viewModelScope.launch {
            val postItemDeferred = RepositoryApi.retrofitService.addItems(itemsRequest)
            try {
                postItemDeferred.await().let {
                    _onSuccess.value = true
                }
            } catch (e: Exception) {
                _onError.value = true
            }

        }
    }
}