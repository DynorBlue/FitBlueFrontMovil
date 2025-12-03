package org.utl.fitblueapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.StateFlow
import org.utl.fitblueapp.entity.Serie

@Dao
interface SerieDao {
    @Query("SELECT * FROM serie where sesionId  = :idSesion")
    fun getSeriesBySesion(idSesion: Long): StateFlow<List<Serie>>

    @Insert
    suspend fun agregarSerie(serie: Serie): Long

    @Update
    suspend fun actualizarSerie(serie: Serie)

    @Delete
    suspend fun eliminarSerie(serie: Serie)
}