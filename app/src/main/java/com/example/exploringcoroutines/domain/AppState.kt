package com.example.exploringcoroutines.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by josephmagara on 25/5/20.
 */

@ExperimentalCoroutinesApi
data class AppState constructor(val lastUpdate: MutableStateFlow<String> = MutableStateFlow("No Update"))