package org.utl.fitblueapp.db.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.utl.fitblueapp.db.repository.SerieRepositorio
import org.utl.fitblueapp.db.entity.Serie

class SerieViewModel (val repositorio: SerieRepositorio): ViewModel(){

    //estado de UI
    private val  _uiState = mutableStateOf(SerieUiState())
    val uiState: State<SerieUiState> = _uiState

    //variable para trabajar con la FK
     private val _sesionId = mutableStateOf<Long?>(null)
     val sesionId: Long? get() = _sesionId.value

    // funcion pR mostrar la lista de series por sesion
    fun getSeriesBySesion(idSesion: Long): StateFlow<List<Serie>>{
        _sesionId.value = idSesion
        return repositorio.getSeriesBySesion(idSesion).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    //funcion para agregar serie

    fun agregarSerie(
        sesionId: Long,
        peso: Double,
        repeticiones: Int

    ) = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            mensaje = null,
            error = null
        )
        try {
            val serie = Serie(
                sesionId = sesionId,
                peso = peso,
                repeticiones = repeticiones
            )
            repositorio.agregarSerie(serie)

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                mensaje = "Serie agregada correctamente",
                error = null
            )
        } catch (e: Exception){
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                mensaje = null,
                error = "No se pudo crear la serie: ${e.message}"
            )
        }
    }
    //funcion eliminar serie
    fun eliminarSerie(serie: Serie) = viewModelScope.launch {
        repositorio.eliminarSerie(serie)
    }
    //funcion actualizar serie
    fun actualizarSerie(serie: Serie) = viewModelScope.launch {
        repositorio.actualizarSerie(serie)
    }
    //funcion limpiar mensaje
    fun limpiarMensaje(){
        _uiState.value = _uiState.value.copy(
            mensaje = null,
            error = null
        )
    }
}

data class SerieUiState(
    val isLoading: Boolean = false,
    val mensaje: String? = null,
    val error: String? = null
)