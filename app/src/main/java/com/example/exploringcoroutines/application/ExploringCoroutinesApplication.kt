package com.example.exploringcoroutines.application

import android.app.Application
import com.example.exploringcoroutines.application.injection.applicationModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by josephmagara on 25/5/20.
 */

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class ExploringCoroutinesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ExploringCoroutinesApplication)
            modules(applicationModules)
        }
    }
}