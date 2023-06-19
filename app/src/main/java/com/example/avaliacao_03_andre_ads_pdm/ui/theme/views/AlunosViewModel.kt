package com.example.avaliacao_03_andre_ads_pdm.ui.theme.views

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avaliacao_03_andre_ads_pdm.data.Aluno
import com.example.avaliacao_03_andre_ads_pdm.network.ChapecoenseApi
import com.example.avaliacao_03_andre_ads_pdm.network.ChapecoenseApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AlunosUiState {
    object Loading: AlunosUiState

    data class Success(val alunos: List<Aluno>): AlunosUiState

    object Error: AlunosUiState
}

class AlunosViewModel: ViewModel() {
    private var _uiState: MutableStateFlow<AlunosUiState> = MutableStateFlow(AlunosUiState.Loading)
    val uiState: StateFlow<AlunosUiState> = _uiState.asStateFlow()

    init {
        getAlunos()
    }

    private fun getAlunos() {
        viewModelScope.launch {
            try {
                _uiState.value = AlunosUiState.Success(ChapecoenseApi.retrofitService.getAlunos())
            } catch(e: IOException) {
                _uiState.value = AlunosUiState.Error
            } catch(e: HttpException) {
                _uiState.value = AlunosUiState.Error
            }
        }
    }
}