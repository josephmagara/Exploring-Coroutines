package com.example.exploringcoroutines.presentation.firstactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.exploringcoroutines.R
import com.example.exploringcoroutines.presentation.secondactivity.SecondActivity
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
            first_view_model_counter.text = it.count
            first_view_counter_subtitle.text = it.updateSourceDescription
        })

        firstViewModel.lastUseCaseUpdate().observe(this, Observer {
            usecase_counter.text = it.count
            first_view_usecase_counter_subtitle.text = it.updateSourceDescription
        })

        go_to_second_activity_button.setOnClickListener {
            startSecondActivity()
        }
    }

    private fun startSecondActivity(){
        val intent = Intent(baseContext, SecondActivity::class.java)
        startActivity(intent)
        finish()
    }
}
