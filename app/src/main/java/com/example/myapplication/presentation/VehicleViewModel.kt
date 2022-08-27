package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.Vehicle
import com.example.myapplication.domain.usecase.GetAllVehiclesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(val getAllVehiclesUseCase: GetAllVehiclesUseCase) :
    ViewModel() {

    val uiState: StateFlow<VehicleUiState> = getAllVehiclesUseCase().map { data ->
        VehicleUiState.Success(data.vehicles)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500),
        initialValue = VehicleUiState.Loading
    )
}

sealed interface VehicleUiState {
    data class Success(val data: List<Vehicle>) : VehicleUiState
    object Error : VehicleUiState
    object Loading : VehicleUiState
}