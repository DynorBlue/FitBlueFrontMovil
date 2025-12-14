package org.utl.fitblueapp.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "ejercicio",
    foreignKeys = [ForeignKey(
        entity = Categoria::class,
        parentColumns = ["idCategoria"],
        childColumns = ["categoriaId"],
        onDelete = ForeignKey.Companion.CASCADE
    )],
    indices = [Index("categoriaId")])
data class Ejercicio(
    @PrimaryKey(autoGenerate = true)
    val idEjercicio: Long = 0,
    val nombreEjercicio: String,
    val descripcion: String? = null,
    val categoriaId: Long
)