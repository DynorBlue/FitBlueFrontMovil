package org.utl.fitblueapp.db.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.utl.fitblueapp.db.repository.SerieRepositorio

@ExperimentalCoroutinesApi
class SerieViewModelFactory(
    private val repositorio: SerieRepositorio,
    private val sesionId: Long? = null
) : ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SerieViewModel::class.java)) {
            return SerieViewModel(repositorio, sesionId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}