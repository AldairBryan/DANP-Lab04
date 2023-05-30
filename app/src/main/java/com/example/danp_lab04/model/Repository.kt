package com.example.danp_lab04.model

import com.example.danp_lab04.entities.*

class Repository(private val appDatabase: AppDatabase) {

    suspend fun getAllEstudiantes(): List<EstudianteEntity> {
        return appDatabase.estudianteDao().getAll()
    }

    suspend fun getCursoConEstudiantes(): List<CursoConEstudiante> {
        return appDatabase.cursoDao().getCoursesWithStudents()
    }

    suspend fun insertEstudiantes(studentsEntity: List<EstudianteEntity>) {
        appDatabase.estudianteDao().insert(studentsEntity)
    }

    suspend fun insertCursos(courses: List<CursoEntity>) {
        appDatabase.cursoDao().insert(courses)
    }

    suspend fun insertEstudianteConCurso(crossRef: List<EstudianteCursoCrossRef>) {
        appDatabase.estudianteDao().insertUserCourseCrossRef(crossRef)
    }

    suspend fun insertCurso(course: CursoEntity) {
        appDatabase.cursoDao().insert(course)
    }

    suspend fun getAllCursos(): List<CursoEntity> {
        return appDatabase.cursoDao().getAll()
    }
}