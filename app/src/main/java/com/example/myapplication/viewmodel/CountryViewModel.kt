package com.example.myapplication.viewmodel

import CountryRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

const val TAG = "CountryViewModel"

class CountryViewModel(
    private val repository: CountryRepository,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _countryListData = MutableLiveData<UIState>()
    val countryListData: LiveData<UIState> get() = _countryListData

    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(TAG, "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}",throwable)
        }
    }

    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    init {
        getCountries()
    }

    fun getCountries() {

        viewModelSafeScope.launch(dispatcher) {
            repository.getCountry().collect { state ->
                _countryListData.postValue(state)
            }
        }
    }

}