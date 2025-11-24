package org.utl.fitblueapp.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "serie",
    foreignKeys = [ForeignKey(
        entity = Serie::class,
        childColumns = ["serieId"],
        parentColumns = ["idSerie"],
        onDelete = ForeignKey.Companion.CASCADE
    )],
    indices = [Index("serieId")])
data class Serie(
    @PrimaryKey(autoGenerate = true)
    val idSerie: Long = 0,
    val sesionId: Long,
    val peso: Double,
    val repeticiones: Int
)