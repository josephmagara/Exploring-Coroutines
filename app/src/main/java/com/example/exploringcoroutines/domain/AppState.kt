package com.example.exploringcoroutines.domain

import com.example.exploringcoroutines.domain.update.model.UpdateSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by josephmagara on 25/5/20.
 */

@ExperimentalCoroutinesApi
data class AppState constructor(val lastUpdate: MutableStateFlow<UpdateSource?> = MutableStateFlow(null))