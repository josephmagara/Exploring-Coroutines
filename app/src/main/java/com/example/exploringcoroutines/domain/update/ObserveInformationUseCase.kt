package com.example.exploringcoroutines.domain.update

import com.example.exploringcoroutines.domain.AppState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

/**
 * Created by josephmagara on 25/5/20.
 */
@ExperimentalCoroutinesApi
class ObserveInformationUseCase(private val appState: AppState) {

    fun lastUpdateFlow(): Flow<String> = appState.lastUpdate
}