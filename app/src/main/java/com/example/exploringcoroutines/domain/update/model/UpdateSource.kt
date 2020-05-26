package com.example.exploringcoroutines.domain.update.model

/**
 * Created by josephmagara on 25/5/20.
 */

sealed class UpdateSource {
    data class UseCase(val updateCount: Int, val description: String) : UpdateSource()
    data class ViewModel(val update: ViewModelUpdate): UpdateSource()
}

sealed class ViewModelUpdate(val updateCount: Int, val description: String) {
    data class FromFirstViewModel(val count: Int) : ViewModelUpdate(count, "First View Model")
    data class FromSecondViewModel(val count: Int) : ViewModelUpdate(count, "Second View Model")
}