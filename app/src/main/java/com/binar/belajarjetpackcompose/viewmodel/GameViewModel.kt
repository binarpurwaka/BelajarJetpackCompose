package com.binar.belajarjetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.belajarjetpackcompose.dataclass.Game
import com.binar.belajarjetpackcompose.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {
    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games: StateFlow<List<Game>> = _games

    init {
        fetchGames()
    }

    private fun fetchGames() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getGames()
                if (response.isSuccessful) {
                    _games.value = response.body() ?: emptyList()
                }
            } catch (e: Exception) {
                // Handle error (e.g., show a toast or log the error)
                e.printStackTrace()
            }
        }
    }
}