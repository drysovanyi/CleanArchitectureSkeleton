package com.dmytrorysovanyi.cleanarchitectureskeleton.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
  // @IoDispatcher dispatcher: CoroutineDispatcher

): ViewModel() {
}