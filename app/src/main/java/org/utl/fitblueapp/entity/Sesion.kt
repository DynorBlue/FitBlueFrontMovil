package org.utl.fitblueapp.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "sesion",
    foreignKeys = [ForeignKey(
        entity = Ejercicio::class,
        parentColumns = ["idEjercicio"],
        childColumns = ["ejercicioId"],
        onDelete = ForeignKey.Companion.CASCADE
    )], indices = [Index("ejercicioId")]
)
data class Sesion(
    @PrimaryKey(autoGenerate = true)
    val idSesion: Long = 0,
    val numeroSesion: Long,
    val fecha: Date,
    val ejercicioId: Long
)