package com.example.exploringcoroutines.application.injection

import com.example.exploringcoroutines.domain.AppState
import com.example.exploringcoroutines.domain.update.ObserveInformationUseCase
import com.example.exploringcoroutines.domain.update.UpdateAppStateUseCase
import com.example.exploringcoroutines.presentation.firstactivity.FirstViewModel
import com.example.exploringcoroutines.presentation.secondactivity.SecondViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by josephmagara on 25/5/20.
 */
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
val viewModelModule = module {
    viewModel { SecondViewModel(get(), get()) }
    viewModel { FirstViewModel(get(), get()) }
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
val domainModule = module {
    factory { ObserveInformationUseCase(get()) }
    factory { UpdateAppStateUseCase(get()) }
    single { AppState() }
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
val applicationModules = listOf(viewModelModule, domainModule)
