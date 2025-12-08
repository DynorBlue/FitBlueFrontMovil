package org.utl.fitblueapp.db.` repository`

import kotlinx.coroutines.flow.Flow
import org.utl.fitblueapp.db.dao.SesionDao
import org.utl.fitblueapp.db.entity.Sesion

class SesionRepositorio(val sesionDao: SesionDao) {

    //obtener las sesiones por ejercicio
    fun getSesionByEjercicio(idEjercicio: Long): Flow<List<Sesion>> {
        return sesionDao.getSesionByEjercicio(idEjercicio)
    }

    //insertar sesiones
    suspend fun agregarSesiones(sesion: Sesion): Long {
        return try {
            sesionDao.agregarSesion(sesion)
        } catch (e: Exception) {
            throw e
        }
    }

    //actualizar sesiones
    suspend fun actualizarSesiones(sesion: Sesion) {
        try {
            sesionDao.actualizarSesion(sesion)
        } catch (e: Exception) {
            throw e
        }
    }

    // eliminar sesiones
    suspend fun eliminarSesiones(sesion: Sesion) {
        try {
            sesionDao.eliminarSesion(sesion)
        } catch (e: Exception) {
            throw e
        }
    }
}