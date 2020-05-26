package com.example.exploringcoroutines.presentation.secondactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploringcoroutines.domain.update.ObserveInformationUseCase
import com.example.exploringcoroutines.domain.update.UpdateAppStateUseCase
import com.example.exploringcoroutines.domain.update.model.UpdateSource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

/**
 * Created by josephmagara on 25/5/20.
 */

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class SecondViewModel(
    private val updateAppStateUseCase: UpdateAppStateUseCase,
    private val observeInformationUseCase: ObserveInformationUseCase
) : ViewModel() {
    private val lastViewModelUpdate: MutableLiveData<String> = MutableLiveData()
    private val lastUseCaseUpdate: MutableLiveData<String> = MutableLiveData()
    private lateinit var updateStateJob: Job

    init {
        viewModelScope.launch {
            observeInformationUseCase.lastUpdateFlow().collect {
                lastViewModelUpdate.value = it
            }
        }
    }

    fun startClicked(){
        //This binds the lifetime of this coroutine to the lifecycle of this view-model
        updateStateJob = viewModelScope.launch {
            repeat(1000){
                updateAppStateUseCase.updateState(it, UpdateSource.SECOND_VIEW_MODEL)
                delay(500L)
            }
        }
    }

    fun stopClicked(){
        if(this::updateStateJob.isInitialized){
            updateStateJob.cancel()
        }
    }

    fun lastViewModelUpdate(): LiveData<String> = lastViewModelUpdate

    fun lastUseCaseUpdate(): LiveData<String> = lastUseCaseUpdate
}