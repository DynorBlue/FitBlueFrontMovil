package org.utl.fitblueapp.db

import kotlinx.coroutines.flow.Flow
import org.utl.fitblueapp.dao.SesionDao
import org.utl.fitblueapp.entity.Sesion

class SesionRepositorio (val sesionDao: SesionDao){

    //obtener las sesiones por ejercicio
    fun getSesionByEjercicio(idEjercicio: Long): Flow<List<Sesion>>{
      return  sesionDao.getSesionByEjercicio(idEjercicio)
    }
    //insertar sesiones
    suspend fun agregarSesiones(sesion: Sesion): Long{
        return sesionDao.agregarSesion(sesion)
    }
    //actualizar sesiones
    suspend fun actualizarSesiones(sesion: Sesion){
        sesionDao.actualizarSesion(sesion)
    }
    // eliminar sesiones
    suspend fun eliminarSesiones(sesion: Sesion){
        sesionDao.eliminarSesion(sesion)
    }
}