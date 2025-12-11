package org.utl.fitblueapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.utl.fitblueapp.db.entity.Serie

@Dao
interface SerieDao {
    @Query("SELECT * FROM serie where sesionId = :idSesion")
    fun getSeriesBySesion(idSesion: Long): Flow<List<Serie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarSerie(serie: Serie): Long

    @Update
    suspend fun actualizarSerie(serie: Serie)

    @Delete
    suspend fun eliminarSerie(serie: Serie)
}