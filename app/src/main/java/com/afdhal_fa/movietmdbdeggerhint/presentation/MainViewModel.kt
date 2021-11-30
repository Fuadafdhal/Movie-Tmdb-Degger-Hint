package com.afdhal_fa.movietmdbdeggerhint.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afdhal_fa.movietmdbdeggerhint.data.vo.Result
import com.afdhal_fa.movietmdbdeggerhint.domain.entity.User
import com.afdhal_fa.movietmdbdeggerhint.domain.usecase.FetchUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Muh Fuad Afdhal on 11/30/2021
 * Email: fuad.afdal.fa@gmail.com
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userUseCase: FetchUserUseCase
) : ViewModel() {

    private val _user = MutableLiveData<Result<User>>()
    val user: LiveData<Result<User>>
        get() = _user

    fun fetchUser() {
        _user.value = Result.Loading
        viewModelScope.launch {
            val userProfileDeferred = async { userUseCase.invoke("Fuadafdhal") }
            val userProfile = userProfileDeferred.await()
            _user.value = userProfile
        }
    }
}