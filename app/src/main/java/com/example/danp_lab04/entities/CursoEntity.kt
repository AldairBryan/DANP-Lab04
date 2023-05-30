package com.example.danp_lab04.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "curso")
data class CursoEntity (
    @PrimaryKey(autoGenerate = true)
    val cursoId:Int,
    val nombre:String,
)
{
    constructor(nombre: String) : this(0, nombre)
}