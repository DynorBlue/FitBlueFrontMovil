package org.utl.fitblueapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.utl.fitblueapp.db.entity.Categoria
@Dao
interface CategoriaDao {

    //funciones
    // para traer las categorias
    @Query("SELECT * FROM categoria")
    fun getAllCategorias(): Flow<List<Categoria>>

    //insertar categorias
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategorias(categoria: Categoria): Long


    //eliminar categorias
    @Delete
    suspend fun eliminarCategoria(categoria: Categoria)

    //actualizar categorias
    @Update
    suspend fun actualizarCategoria(categoria: Categoria)

}