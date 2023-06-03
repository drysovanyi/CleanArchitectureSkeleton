package com.dmytrorysovanyi.cleanarchitectureskeleton.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmytrorysovanyi.cleanarchitectureskeleton.mappers.HelloWorldUiMapper
import com.dmytrorysovanyi.cleanarchitectureskeleton.models.HelloWorldState
import com.dmytrorysovanyi.domain.features.hello.GetHelloWorldUseCase
import com.dmytrorysovanyi.domain.features.hello.RefreshHelloWorldUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HelloWorldViewModel @Inject constructor(
    private val getHelloWorldUseCase: GetHelloWorldUseCase,
    private val refreshHelloWorldUseCase: RefreshHelloWorldUseCase,
    private val mapper: HelloWorldUiMapper,
) : ViewModel() {

    var state by mutableStateOf(
        HelloWorldState(isRefreshing = true, emptyList()),
    )

    init {
        refreshHelloWorld()
        initHelloWorld()
    }

    private fun refreshHelloWorld() {
        viewModelScope.launch {
            state = state.copy(isRefreshing = true)
            refreshHelloWorldUseCase.invoke()
        }
    }

    private fun initHelloWorld() {
        getHelloWorldUseCase()
            .onEach { domainModel ->
                val uiState = HelloWorldState(isRefreshing = false, stringList = mapper.mapHelloWorldModelToUi(domainModel))
                state = uiState
            }.catch {
                it.printStackTrace()
            }.launchIn(viewModelScope)
    }

    fun refresh() {
        refreshHelloWorld()
    }
}
