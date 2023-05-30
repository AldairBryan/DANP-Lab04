package com.example.danp_lab04.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estudiante")
data class EstudianteEntity(
    @PrimaryKey
    val estudianteId: Int,

    @ColumnInfo(name="apellido_nombre")
    val fullname: String
)