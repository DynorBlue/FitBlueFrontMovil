package org.utl.fitblueapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.utl.fitblueapp.entity.Categoria
import org.utl.fitblueapp.entity.Ejercicio

@Dao
interface EjercicioDao{

    //obtener los ejercicios solo de cierta categoria
    @Query("select * from ejercicio where categoriaId = :idCategoria ")
    fun getAllEjercicioByCategoria(idCategoria: Long): Flow<List<Ejercicio>>

    //insertar nuevo ejercicio
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEjercicio(ejercicio: Ejercicio): Long

    //eliminar ejercicio
    @Delete
    suspend fun eliminarEjercicio(ejercicio: Ejercicio)


    //actualizar ejercicio
    @Update
    suspend fun actualizarEjercicio(ejercicio: Ejercicio)

}