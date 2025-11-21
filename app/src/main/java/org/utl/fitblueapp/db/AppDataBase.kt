package org.utl.fitblueapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.utl.fitblueapp.dao.CategoriaDao
import org.utl.fitblueapp.dao.EjercicioDao
import org.utl.fitblueapp.dao.SesionDao
import org.utl.fitblueapp.entity.Categoria
import org.utl.fitblueapp.entity.Ejercicio
import org.utl.fitblueapp.entity.Sesion

@Database(
    //agregar las demas entidades dentro de los corchetes
    entities = [Categoria::class, Ejercicio::class, Sesion::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun categoriaDao(): CategoriaDao
    abstract fun ejercicioDao(): EjercicioDao
    abstract fun sesionDao(): SesionDao
    // demas funciones de las otrass entidades

     companion object{
         @Volatile
         private var INSTANCE: AppDataBase? = null

         fun getDataBase(context: Context): AppDataBase{
             return INSTANCE ?: synchronized(this){
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     AppDataBase::class.java,
                     "FitBlue_DataBase"
                 ).build()
                 INSTANCE = instance
                 return instance
             }
         }
     }
}