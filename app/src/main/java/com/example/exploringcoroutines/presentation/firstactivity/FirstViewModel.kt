package com.example.exploringcoroutines.presentation.firstactivity

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
class FirstViewModel(
    private val updateAppStateUseCase: UpdateAppStateUseCase,
    private val observeInformationUseCase: ObserveInformationUseCase
) : ViewModel() {

    private val lastUpdate: MutableLiveData<String> = MutableLiveData()
    private lateinit var updateStateJob: Job
    init {
        viewModelScope.launch {
            observeInformationUseCase.lastUpdateFlow().collect {
                lastUpdate.value = it
            }
        }
    }

    fun startClicked(){
        //This binds the lifetime of this coroutine to the lifecycle of this view-model
        updateStateJob = viewModelScope.launch {
            repeat(1000){
                updateAppStateUseCase.updateState(it, UpdateSource.FIRST_VIEW_MODEL)
                delay(500L)
            }
        }
    }

    fun stopClicked(){
        if(this::updateStateJob.isInitialized){
            updateStateJob.cancel()
        }
    }

    fun lastUpdate(): LiveData<String> = lastUpdate
}