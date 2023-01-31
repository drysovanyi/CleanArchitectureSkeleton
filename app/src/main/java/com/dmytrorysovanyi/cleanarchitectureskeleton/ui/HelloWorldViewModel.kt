package com.dmytrorysovanyi.cleanarchitectureskeleton.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmytrorysovanyi.cleanarchitectureskeleton.models.HelloWorldState
import com.dmytrorysovanyi.domain.features.hello.GetHelloWorldUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HelloWorldViewModel @Inject constructor(
  private val getHelloWorldUseCase: GetHelloWorldUseCase
) : ViewModel() {

  var state by mutableStateOf(
    HelloWorldState("")
  )

  init {
    getHelloWorld()
  }

  fun getHelloWorld() {
    viewModelScope.launch {
      val helloWorld = async {
        getHelloWorldUseCase()
      }
      val result = helloWorld.await()

      result.onSuccess {
        state = state.copy(text = it.text)
      }.onFailure {
        it.printStackTrace()
      }
    }
  }
}