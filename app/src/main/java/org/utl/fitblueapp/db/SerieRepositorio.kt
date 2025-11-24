package org.utl.fitblueapp.db

import kotlinx.coroutines.flow.StateFlow
import org.utl.fitblueapp.dao.SerieDao
import org.utl.fitblueapp.entity.Serie


class SerieRepositorio(val serieDao: SerieDao) {

    fun getSeriesBySesion(idSesion: Long): StateFlow<List<Serie>> {

        return serieDao.getSeriesBySesion(idSesion)
    }

    suspend fun agregarSerie(serie: Serie): Long{
        return serieDao.agregarSerie(serie)
    }

    suspend fun actualizarSerie(serie: Serie){
        serieDao.actualizarSerie(serie)
    }

    suspend fun eliminarEjercicio(serie: Serie){
        serieDao.eliminarSerie(serie)
    }


}