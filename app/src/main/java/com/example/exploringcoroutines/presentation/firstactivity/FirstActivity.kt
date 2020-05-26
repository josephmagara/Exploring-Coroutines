package com.example.exploringcoroutines.presentation.firstactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.exploringcoroutines.R
import kotlinx.android.synthetic.main.first_activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class FirstActivity : AppCompatActivity() {

    private val firstViewModel: FirstViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_activity_main)

        first_button_start_emitting.setOnClickListener {
            firstViewModel.startClicked()
        }

        first_button_stop_emitting.setOnClickListener {
            firstViewModel.stopClicked()
        }

        firstViewModel.lastViewModelUpdate().observe(this, Observer {
            first_view_model_counter.text = it
        })
    }
}
