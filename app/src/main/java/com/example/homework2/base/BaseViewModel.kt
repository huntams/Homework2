package com.example.homework2.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework2.data.remote.ResultLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    fun <T> MutableLiveData<ResultLoader<T>>.loadData(
        block: suspend () -> T,
    ) {
        viewModelScope.launch {
            flow {

                try {
                    emit(ResultLoader.Loading<T>())
                    emit(ResultLoader.Success(block))
                } catch (t: Throwable) {
                    emit(ResultLoader.Failure<T>(t))
                }
            }.collect{ result ->
                postValue(result as ResultLoader<T>?)
            }
        }
    }

}
