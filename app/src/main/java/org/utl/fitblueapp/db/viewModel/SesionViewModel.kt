package org.utl.fitblueapp.db.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.utl.fitblueapp.db.repository.SesionRepositorio
import org.utl.fitblueapp.db.entity.Sesion
import java.util.Date

@ExperimentalCoroutinesApi
class SesionViewModel(
    val repositorio: SesionRepositorio,
    initialEjercicioId: Long? = null
): ViewModel(){
    //estado de la UI
    private val _uiState = mutableStateOf(SesionUiState())
    val uiState: State<SesionUiState> = _uiState
//variable para trabajar con la FK
    private val _ejercicioId = MutableStateFlow<Long?>(null)
    val ejercicioId: StateFlow<Long?> get() = _ejercicioId

    //lista de sesiones reactivas por ejercicio usando flatMapLatest
    val sesionesPorEjercicio: StateFlow<List<Sesion>> = _ejercicioId
        .flatMapLatest { ejercicioId ->
            if (ejercicioId != null) {
                repositorio.getSesionByEjercicio(ejercicioId)
            } else {
                flowOf(emptyList())
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        initialEjercicioId?.let { _ejercicioId.value = it }
    }

    //función para cambiar el ejercicio y actualizar la lista de sesiones
    fun setEjercicio(idEjercicio: Long) {
        _ejercicioId.value = idEjercicio
    }

    //método de compatibilidad para código existente
    fun getSesionesByEjercicio(idEjercicio: Long): StateFlow<List<Sesion>> {
        setEjercicio(idEjercicio)
        return sesionesPorEjercicio
    }
    //funcion agregar sesion
    fun agregarSesion(
        numeroSesion: Long,
        fecha: Date,
        ejercicioId: Long
    ) = viewModelScope.launch{
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            mensaje = null,
            error= null
        )
        try {
            val sesion = Sesion(
                numeroSesion = numeroSesion,
                fecha = fecha,
                ejercicioId = ejercicioId
            )
            repositorio.agregarSesiones(sesion)

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                mensaje = "Sesion agregada correctamente",
                error = null
            )
        } catch (e: Exception){
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                mensaje = null,
                error = "No se pudo crear la sesion: ${e.message}"
            )
        }
    }
    //funcion eliminar sesion
    fun eliminarSesion(sesion: Sesion) = viewModelScope.launch {
        repositorio.eliminarSesiones(sesion)
    }
    //funcion actualizar sesion
    fun actualizarSesion(sesion: Sesion) = viewModelScope.launch {
        repositorio.actualizarSesiones(sesion)
    }
    //funcion limpiar Mensaje
    fun limpiarMensaje(){
        _uiState.value = _uiState.value.copy(
            mensaje = null,
            error = null
        )
    }
}

data class SesionUiState(
    val isLoading: Boolean = false,
    val mensaje: String? = null,
    val error: String? = null
)