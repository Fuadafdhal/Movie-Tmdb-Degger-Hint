package com.afdhal_fa.movietmdbdeggerhint.presentation

import android.os.Bundle
import androidx.activity.viewModels
import com.afdhal_fa.movietmdbdeggerhint.R
import com.afdhal_fa.movietmdbdeggerhint.abstraction.BaseActivity
import com.afdhal_fa.movietmdbdeggerhint.data.vo.Result
import com.afdhal_fa.movietmdbdeggerhint.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutResourceId() = R.layout.activity_main

    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.user.observe(this) {
            when (it) {
                is Result.Loading -> {
                    println("Result : isLoading")
                }
                is Result.Success -> {
                    println("Result : isSuccess")
                    val data = it.data
                    println("Data : $data")
                }

                is Result.Error -> {
                    println("Result : isError")
                    println("errorMessage: ${it.errorMessage}")
                }
            }
        }
        vm.fetchUser()
    }
}