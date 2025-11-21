package org.utl.fitblueapp.db

import kotlinx.coroutines.flow.StateFlow
import org.utl.fitblueapp.dao.CategoriaDao
import org.utl.fitblueapp.entity.Categoria

class CategoriaRepositorio(private val categoriaDao: CategoriaDao){

    // declaramos val para que sea reactivo es decir se actualize solo
    val allCategorias: StateFlow<List<Categoria>> = categoriaDao.getAllCategorias()

    //  usamos long por que en nuestro dao esta funcion lo pide
    suspend fun insertCategorias(categoria: Categoria): Long{
         return categoriaDao.insertCategorias(categoria)
    }


    // funciones para eliminar y actualizar


    suspend fun eliminarCategorias(categoria: Categoria){
        categoriaDao.eliminarCategoria(categoria)
    }

    suspend fun actualizarCategorias(categoria: Categoria){
        categoriaDao.actualizarCategoria(categoria)
    }
}