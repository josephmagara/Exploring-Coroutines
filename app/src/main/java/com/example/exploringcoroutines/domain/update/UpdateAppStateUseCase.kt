package com.example.exploringcoroutines.domain.update

import com.example.exploringcoroutines.domain.AppState
import com.example.exploringcoroutines.domain.update.model.UpdateSource
import kotlinx.coroutines.*

/**
 * Created by josephmagara on 25/5/20.
 */

@ExperimentalCoroutinesApi
class UpdateAppStateUseCase constructor(private val appState: AppState) {

    private lateinit var useCaseStateUpdateJob: Job

    suspend fun updateState(updateSource: UpdateSource) {
        startUpdatesFromUseCase()
        appState.lastUpdate.value = updateSource
    }

    private fun startUpdatesFromUseCase(){
        if (this::useCaseStateUpdateJob.isInitialized) return
        useCaseStateUpdateJob = GlobalScope.launch {
            repeat(1000) {
                updateState(UpdateSource.UseCase(it))
                delay(700L)
            }
        }
    }
}