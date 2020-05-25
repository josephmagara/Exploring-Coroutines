package com.example.exploringcoroutines.domain.update

import com.example.exploringcoroutines.domain.AppState
import com.example.exploringcoroutines.domain.update.model.UpdateSource
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by josephmagara on 25/5/20.
 */

@ExperimentalCoroutinesApi
class UpdateAppStateUseCase constructor(private val appState: AppState) {

    @ExperimentalCoroutinesApi
    suspend fun updateState(updateCount: Int, updateSource: UpdateSource) {
        appState.lastUpdate.value = "$updateCount update from ${updateSource.stringValue}"
    }
}