package org.utl.fitblueapp.db

import kotlinx.coroutines.flow.Flow
import org.utl.fitblueapp.dao.EjercicioDao
import org.utl.fitblueapp.entity.Ejercicio

class EjercicioRepositorio(private val ejercicioDao: EjercicioDao){

    //funcion mostrar ejercicios ( no usamos val por que en este caso estaremos trayendo categorias especificas)
    fun getEjerciciosPorCategoria(idCategoria: Long): Flow<List<Ejercicio>>{
        return ejercicioDao.getAllEjercicioByCategoria(idCategoria)
    }

    suspend fun insertarEjercicio(ejercicio: Ejercicio): Long{
      return  ejercicioDao.insertEjercicio(ejercicio)
    }

    suspend fun eliminarEjercicio(ejercicio: Ejercicio){
        ejercicioDao.eliminarEjercicio(ejercicio)
    }

    suspend fun actualizarEjercicio(ejercicio: Ejercicio){
        ejercicioDao.actualizarEjercicio(ejercicio)
    }
}