package org.utl.fitblueapp.db.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.utl.fitblueapp.db.repository.EjercicioRepositorio

@ExperimentalCoroutinesApi
class EjercicioViewModelFactory(
    private val repositorio: EjercicioRepositorio,
    private val categoriaId: Long? = null
) : ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EjercicioViewModel::class.java)) {
            return EjercicioViewModel(repositorio, categoriaId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}