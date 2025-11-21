package org.utl.fitblueapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.StateFlow
import org.utl.fitblueapp.entity.Sesion

@Dao
interface SesionDao {
    //obtener todas las seciones por ejercicio
    @Query("Select * from sesion where ejercicioId = :idEjercicio")
    fun getSesionByEjercicio(idEjercicio: Long): StateFlow<List<Sesion>>
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