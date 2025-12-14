package org.utl.fitblueapp.db.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.utl.fitblueapp.db.repository.CategoriaRepositorio

class CategoriaViewModelFactory(
    private val repositorio: CategoriaRepositorio
) : ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriaViewModel::class.java)) {
            return CategoriaViewModel(repositorio) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}