package org.utl.fitblueapp.db.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.utl.fitblueapp.db.repository.SesionRepositorio

@ExperimentalCoroutinesApi
class SesionViewModelFactory(
    private val repositorio: SesionRepositorio,
    private val ejercicioId: Long? = null
) : ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SesionViewModel::class.java)) {
            return SesionViewModel(repositorio, ejercicioId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}