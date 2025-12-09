package org.utl.fitblueapp.db.repository

import kotlinx.coroutines.flow.Flow
import org.utl.fitblueapp.db.dao.EjercicioDao
import org.utl.fitblueapp.db.entity.Ejercicio

class EjercicioRepositorio(private val ejercicioDao: EjercicioDao) {

    //funcion mostrar ejercicios ( no usamos val por que en este caso estaremos trayendo categorias especificas)
    fun getEjerciciosPorCategoria(idCategoria: Long): Flow<List<Ejercicio>> {
        return ejercicioDao.getAllEjercicioByCategoria(idCategoria)
    }

    suspend fun insertarEjercicio(ejercicio: Ejercicio): Long {
        return try {
            ejercicioDao.insertEjercicio(ejercicio)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun eliminarEjercicio(ejercicio: Ejercicio) {
        try {
            ejercicioDao.eliminarEjercicio(ejercicio)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun actualizarEjercicio(ejercicio: Ejercicio) {
        try {
            ejercicioDao.actualizarEjercicio(ejercicio)
        } catch (e: Exception) {
            throw e
        }
    }
}