package ru.mrapple100.intentneyavniy0312

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import ru.mrapple100.intentneyavniy0312.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this,R.layout.activity_main)
        setContentView(binding.root)

        val viewModel: BaseViewModel =
            ViewModelProvider(this)[BaseViewModel::class.java]
        viewModel.initViewModel(this,binding)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}