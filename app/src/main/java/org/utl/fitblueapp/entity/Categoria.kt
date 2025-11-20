package org.utl.fitblueapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "categoria")
data class Categoria(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombre: String
)