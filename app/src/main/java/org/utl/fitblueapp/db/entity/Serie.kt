package org.utl.fitblueapp.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "serie",
    foreignKeys = [ForeignKey(
        entity = Sesion::class,
        childColumns = ["sesionId"],
        parentColumns = ["idSesion"],
        onDelete= ForeignKey.Companion.CASCADE
    )],
    indices = [Index("sesionId")])
data class Serie(
    @PrimaryKey(autoGenerate = true)
    val idSerie: Long = 0,
    val sesionId: Long,
    val peso: Double,
    val repeticiones: Int
)