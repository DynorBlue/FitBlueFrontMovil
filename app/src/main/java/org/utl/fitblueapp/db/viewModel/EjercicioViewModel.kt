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
import org.utl.fitblueapp.db.repository.EjercicioRepositorio
import org.utl.fitblueapp.db.entity.Ejercicio

@ExperimentalCoroutinesApi
class EjercicioViewModel(
    private val repositorio: EjercicioRepositorio,
    initialCategoriaId: Long? = null
) : ViewModel() {

    //estado de la UI
    private val _uiState = mutableStateOf(EjercicioUiState())
    val uiState: State<EjercicioUiState> = _uiState

  //con esta variable seleccionada la categoria actual que nos dara el id para despues
    // mostrar la lista de los ejercicios de esa categoria
    private val _categoriaId = MutableStateFlow<Long?>(null)
    val categoriaId: StateFlow<Long?> get() = _categoriaId

    //lista de ejercicios reactivos por su categoria usando flatMapLatest
    val ejerciciosPorCategoria: StateFlow<List<Ejercicio>> = _categoriaId
        .flatMapLatest { categoriaId ->
            if (categoriaId != null) {
                repositorio.getEjerciciosPorCategoria(categoriaId)
            } else {
                kotlinx.coroutines.flow.flowOf(emptyList())
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        initialCategoriaId?.let { _categoriaId.value = it }
    }

    //función para cambiar la categoría y actualizar la lista de ejercicios
    fun setCategoria(idCategoria: Long) {
        _categoriaId.value = idCategoria
    }

    //funcion para agregar ejercicio con FK
    fun agregarEjercicio(
        nombreEjercicio: String,
        descripcion: String?,
        categoriaId: Long
    ) = viewModelScope.launch {

        _uiState.value = _uiState.value.copy(
            isLoading = true,
            mensaje = null,
            error = null
        )

        try {
            val ejercicio = Ejercicio(
                nombreEjercicio = nombreEjercicio,
                descripcion = descripcion,
                categoriaId = categoriaId   // FK
            )

            repositorio.insertarEjercicio(ejercicio)

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                mensaje = "Ejercicio agregado correctamente",
                error = null
            )

        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                mensaje = null,
                error = "No se pudo agregar el ejercicio: ${e.message}"
            )
        }
    }

    fun limpiarMensaje() {
        _uiState.value = _uiState.value.copy(
            mensaje = null,
            error = null
        )
    }

    fun actualizarEjercicio(ejercicio: Ejercicio) = viewModelScope.launch {
        repositorio.actualizarEjercicio(ejercicio)
    }

    fun eliminarEjercicio(ejercicio: Ejercicio) = viewModelScope.launch {
        repositorio.eliminarEjercicio(ejercicio)
    }
}

data class EjercicioUiState(
    val isLoading: Boolean = false, //corregir
    val mensaje: String? = null,
    val error: String? = null
)