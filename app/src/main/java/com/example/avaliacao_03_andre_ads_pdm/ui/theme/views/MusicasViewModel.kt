package com.example.avaliacao_03_andre_ads_pdm.ui.theme.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avaliacao_03_andre_ads_pdm.data.Musica
import com.example.avaliacao_03_andre_ads_pdm.network.MusicaApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MusicasUiState {
    object Loading: MusicasUiState

    data class Success(val musicas: List<Musica>): MusicasUiState

    object Error: MusicasUiState
}

class MusicasViewModel: ViewModel() {
    private var _uiState: MutableStateFlow<MusicasUiState> = MutableStateFlow(MusicasUiState.Loading)
    val uiState: StateFlow<MusicasUiState> = _uiState.asStateFlow()

    init {
        getMusicas()
    }

    private fun getMusicas() {
        viewModelScope.launch {
            try {
                _uiState.value = MusicasUiState.Success(MusicaApi.retrofitService.getMusicas())
            } catch(e: IOException) {
                _uiState.value = MusicasUiState.Error
            } catch(e: HttpException) {
                _uiState.value = MusicasUiState.Error
            }
        }
    }
}