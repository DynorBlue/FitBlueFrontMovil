package org.utl.fitblueapp.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.utl.fitblueapp.db.SesionRepositorio
import org.utl.fitblueapp.entity.Sesion
import java.util.Date

class SesionViewModel (val repositorio: SesionRepositorio): ViewModel(){
    //estado de la UI
    private val _uiState = mutableStateOf(SesionIuState())
    val uiState: State<SesionIuState> = _uiState
    //variable para trabajr con la FK
    private val _ejercicioId = mutableStateOf<Long?>(null)
    val ejercicioId: Long? get() = _ejercicioId.value

    //varaible para mostrar la lista de sesiones
    fun getSesionesByEjercicio(idEjercicio: Long): StateFlow<List<Sesion>> {
        _ejercicioId.value = idEjercicio
        return repositorio.getSesionByEjercicio(idEjercicio).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }
    //funcion agregar sesion
    fun agregarSesion(
        numeroSesion: Long,
        fecha: Date,
        ejercicioId: Long
    ) = viewModelScope.launch{
        _uiState.value = _uiState.value.copy(
            isloading = true,
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
                isloading = false,
                mensaje = "Sesion agregada correctamente",
                error = null
            )
        } catch (e: Exception){
            _uiState.value = _uiState.value.copy(
                isloading = false,
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

data class SesionIuState(
    val isloading: Boolean = false,
    val mensaje: String? = null,
    val error: String? = null
)