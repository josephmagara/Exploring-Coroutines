package com.example.exploringcoroutines.presentation.common.model

/**
 * Created by josephmagara on 26/5/20.
 */
data class UpdateSourceViewItem(val updateCount: Int, val updateSourceDescription: String) {
    val count: String get() = "$updateCount"
}