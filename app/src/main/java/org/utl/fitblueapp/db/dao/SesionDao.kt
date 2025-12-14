package org.utl.fitblueapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.utl.fitblueapp.db.entity.Sesion

@Dao
interface SesionDao {
    //obtener todas las seciones por ejercicio
    @Query("SELECT * from sesion where ejercicioId = :idEjercicio")
    fun getSesionByEjercicio(idEjercicio: Long): Flow<List<Sesion>>
    //insertar sesion
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarSesion(sesion: Sesion): Long
    //eliminar sesion
    @Delete
    suspend fun eliminarSesion(sesion: Sesion)
    //actualizar sesion
    @Update
    suspend fun actualizarSesion(sesion: Sesion)

}