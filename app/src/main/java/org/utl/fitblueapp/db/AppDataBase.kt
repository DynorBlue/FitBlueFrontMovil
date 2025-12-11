package org.utl.fitblueapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.utl.fitblueapp.db.dao.CategoriaDao
import org.utl.fitblueapp.db.dao.EjercicioDao
import org.utl.fitblueapp.db.dao.SerieDao
import org.utl.fitblueapp.db.dao.SesionDao
import org.utl.fitblueapp.db.entity.Categoria
import org.utl.fitblueapp.db.entity.Ejercicio
import org.utl.fitblueapp.db.entity.Serie
import org.utl.fitblueapp.db.entity.Sesion

@Database(
    //agregar las demas entidades dentro de los corchetes
    entities = [Categoria::class, Ejercicio::class, Sesion::class, Serie::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ConvertidorFecha::class)
abstract class AppDataBase: RoomDatabase() {
    abstract fun categoriaDao(): CategoriaDao
    abstract fun ejercicioDao(): EjercicioDao
    abstract fun sesionDao(): SesionDao
    abstract fun serieDao(): SerieDao
    // demas funciones de las otrass entidades

     companion object{
         @Volatile
         private var INSTANCE: AppDataBase? = null

         @JvmStatic
        fun getDataBase(context: Context): AppDataBase{
             return INSTANCE ?: synchronized(this){
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     AppDataBase::class.java,
                     "fitblue_database"
                 ).build()
                 INSTANCE = instance
                 return instance
             }
         }
     }
}