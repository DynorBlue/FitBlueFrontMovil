package org.utl.fitblueapp.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.utl.fitblueapp.db.CategoriaRepositorio
import org.utl.fitblueapp.entity.Categoria

class CategoriaViewModel(
    private val repositorio: CategoriaRepositorio
) : ViewModel() {
    //estado de la UI
    private val _uiState = mutableStateOf(CategoriaUiState())
    val uiState: State<CategoriaUiState> = _uiState

    //mostrara la lista de categorias
    //Lista reactiva de categorias
    val categorias = repositorio.allCategorias.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    //funcion para agregar categoria
    fun agregarCategoria(
        nombre: String
    ) = viewModelScope.launch {
        try {
            _uiState.value = _uiState.value.copy(isLoading = true, mensaje = null, error = null)
            val categoria = Categoria(
                nombre = nombre
            )
            repositorio.insertCategorias(categoria)

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                mensaje = "Categoria Agregada correctamente",
                error = null
            )
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                mensaje = null,
                error = "La categoria no fue agregada: ${e.message} "
            )
        }
    }

    //funcion actualizaer categoria
    fun actualizarCategoria(categoria: Categoria) = viewModelScope.launch {
        repositorio.actualizarCategorias(categoria)
    }

    fun eliminarCategoria(categoria: Categoria) = viewModelScope.launch {
        repositorio.eliminarCategorias(categoria)
    }

    //funcion para limmpiar el mensaje
    fun limpiarMensaje() {
        _uiState.value = _uiState.value.copy(mensaje = null, error = null)
    }
}

data class CategoriaUiState(
    val isLoading: Boolean = false,
    val mensaje: String? = null,
    val error: String? = null
)