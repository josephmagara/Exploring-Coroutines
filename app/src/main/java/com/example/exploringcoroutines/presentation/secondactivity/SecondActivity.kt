package com.example.exploringcoroutines.presentation.secondactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.exploringcoroutines.R
import kotlinx.android.synthetic.main.first_activity_main.*
import kotlinx.android.synthetic.main.second_activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class SecondActivity : AppCompatActivity() {

    private val secondViewModel: SecondViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_activity_main)

        second_button_start_emitting.setOnClickListener {
            secondViewModel.startClicked()
        }

        second_button_stop_emitting.setOnClickListener {
            secondViewModel.stopClicked()
        }

        secondViewModel.lastViewModelUpdate().observe(this, Observer {
            second_view_model_counter.text = it.count
            second_view_counter_subtitle.text = it.updateSourceDescription
        })

        secondViewModel.lastUseCaseUpdate().observe(this, Observer {
            second_activity_usecase_counter.text = it.count
            second_view_usecase_counter_subtitle.text = it.updateSourceDescription
        })
    }
}
