package org.utl.fitblueapp.db.` repository`

import kotlinx.coroutines.flow.Flow
import org.utl.fitblueapp.db.dao.SerieDao
import org.utl.fitblueapp.db.entity.Serie

class SerieRepositorio(val serieDao: SerieDao) {

    fun getSeriesBySesion(idSesion: Long): Flow<List<Serie>> {

        return serieDao.getSeriesBySesion(idSesion)
    }

    suspend fun agregarSerie(serie: Serie): Long {
        return try {
            serieDao.agregarSerie(serie)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun actualizarSerie(serie: Serie) {
        try {
            serieDao.actualizarSerie(serie)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun eliminarSerie(serie: Serie) {
        try {
            serieDao.eliminarSerie(serie)
        } catch (e: Exception) {
            throw e
        }
    }


}