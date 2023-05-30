package com.example.danp_lab04.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CursoConEstudiante(
    @Embedded val curso: CursoEntity,
    @Relation(
        parentColumn = "cursoId",
        entityColumn = "estudianteId",
        associateBy = Junction(EstudianteCursoCrossRef::class)
    )
    val students: List<EstudianteEntity>

)