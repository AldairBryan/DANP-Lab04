package com.example.danp_lab04.entities
import androidx.room.Entity
@Entity(primaryKeys = ["studentId", "courseId"])
data class EstudianteCursoCrossRef(
    val estudianteId: Int,
    val cursoId: Int
)