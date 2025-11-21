package org.utl.fitblueapp.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.utl.fitblueapp.entity.Sesion


interface SesionDao {
    //obtener todas las seciones por ejercicio
    @Query("Select * from sesion where ejercicioId = :idEjercicio")
    fun getSesionByEjercicio(idEjercicio: Long): Flow<List<Sesion>>
    //insertar sesion
    @Insert
   suspend fun agregarSesion(sesion: Sesion): Long
    //eliminar sesion
    @Delete
    suspend fun eliminarSesion(sesion: Sesion)
    //actualizar sesion
    @Update
    suspend fun actualizarSesion(sesion: Sesion)

}