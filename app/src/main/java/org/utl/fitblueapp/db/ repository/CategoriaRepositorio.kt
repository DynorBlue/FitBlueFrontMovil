package org.utl.fitblueapp.db.` repository`

import kotlinx.coroutines.flow.Flow
import org.utl.fitblueapp.db.dao.CategoriaDao
import org.utl.fitblueapp.db.entity.Categoria

class CategoriaRepositorio(private val categoriaDao: CategoriaDao) {

    // declaramos val para que sea reactivo es decir se actualize solo
    val allCategorias: Flow<List<Categoria>> = categoriaDao.getAllCategorias()

    //  usamos long por que en nuestro dao esta funcion lo pide
    suspend fun insertCategorias(categoria: Categoria): Long {
        return try {
            categoriaDao.insertCategorias(categoria)
        } catch (e: Exception) {
            throw e
        }
    }


    // funciones para eliminar y actualizar


    suspend fun eliminarCategorias(categoria: Categoria) {
        try {
            categoriaDao.eliminarCategoria(categoria)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun actualizarCategorias(categoria: Categoria) {
        try {
            categoriaDao.actualizarCategoria(categoria)
        } catch (e: Exception) {
            throw e
        }
    }
}