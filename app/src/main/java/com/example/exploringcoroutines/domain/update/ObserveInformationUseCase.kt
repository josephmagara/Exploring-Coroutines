package com.example.exploringcoroutines.domain.update

import com.example.exploringcoroutines.domain.AppState
import com.example.exploringcoroutines.domain.update.model.UpdateSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

/**
 * Created by josephmagara on 25/5/20.
 */
@ExperimentalCoroutinesApi
class ObserveInformationUseCase(private val appState: AppState) {

    fun lastUpdateFlow(): Flow<UpdateSource?> = appState.lastUpdate
}