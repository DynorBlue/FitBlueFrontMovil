package org.utl.fitblueapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "categoria")
data class Categoria(

    //declaramos siempre con val por que estos valores no deben poderser modificables
    //el  @update no los modifica remplaza el valor
    @PrimaryKey(autoGenerate = true)
    val idCategoria: Long = 0,
    val nombre: String
)