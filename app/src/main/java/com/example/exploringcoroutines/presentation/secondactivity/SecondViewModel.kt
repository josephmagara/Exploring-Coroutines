package com.example.exploringcoroutines.presentation.secondactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploringcoroutines.domain.update.ObserveInformationUseCase
import com.example.exploringcoroutines.domain.update.UpdateAppStateUseCase
import com.example.exploringcoroutines.domain.update.model.UpdateSource
import com.example.exploringcoroutines.domain.update.model.ViewModelUpdate
import com.example.exploringcoroutines.presentation.common.model.UpdateSourceViewItem
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

/**
 * Created by josephmagara on 25/5/20.
 */

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class SecondViewModel(
    private val updateAppStateUseCase: UpdateAppStateUseCase,
    private val observeInformationUseCase: ObserveInformationUseCase
) : ViewModel() {
    private val lastViewModelUpdate: MutableLiveData<UpdateSourceViewItem> = MutableLiveData()
    private val lastUseCaseUpdate: MutableLiveData<UpdateSourceViewItem> = MutableLiveData()
    private lateinit var updateStateJob: Job

    init {

        viewModelScope.launch {
            observeInformationUseCase.lastUpdateFlow()
                .filterNotNull()
                .map {
                    when (it){
                        is UpdateSource.ViewModel -> {
                            val viewItem = with(it.update) {
                                UpdateSourceViewItem(updateCount, description)
                            }
                            Pair(it, viewItem)
                        }
                        is UpdateSource.UseCase -> {
                            val viewItem = with(it) {
                                UpdateSourceViewItem(updateCount, description)
                            }
                            Pair(it, viewItem)
                        }
                    }
                }.collect {
                    when(it.first){
                        is UpdateSource.ViewModel -> lastViewModelUpdate.value = it.second
                        is UpdateSource.UseCase -> lastUseCaseUpdate.value = it.second
                    }

                }
        }
    }

    fun startClicked(){
        //This binds the lifetime of this coroutine to the lifecycle of this view-model
        updateStateJob = viewModelScope.launch {
            repeat(1000){
                val update = ViewModelUpdate.FromSecondViewModel(it)
                updateAppStateUseCase.updateState(UpdateSource.ViewModel(update))
                delay(1000L)
            }
        }
    }

    fun stopClicked(){
        if(this::updateStateJob.isInitialized){
            updateStateJob.cancel()
        }
    }

    fun lastViewModelUpdate(): LiveData<UpdateSourceViewItem> = lastViewModelUpdate

    fun lastUseCaseUpdate(): LiveData<UpdateSourceViewItem> = lastUseCaseUpdate
}