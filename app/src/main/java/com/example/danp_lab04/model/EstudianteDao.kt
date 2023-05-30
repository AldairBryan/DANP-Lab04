package com.example.danp_lab04.model

import androidx.room.*
import com.example.danp_lab04.entities.EstudianteCursoCrossRef
import com.example.danp_lab04.entities.EstudianteEntity

@Dao
interface EstudianteDao {
    @Query("select * from estudiante")
    suspend fun getAll(): List<EstudianteEntity>

    @Insert
    suspend fun insert(studentEntity: EstudianteEntity)

    @Insert
    suspend fun insert(studentsEntity: List<EstudianteEntity>)

    @Delete
    suspend fun delete(studentEntity: EstudianteEntity)

    /*@Transaction
    @Query("select * from estudiante")
    suspend fun getStudentWithCourses(): List<EstudianteConCurso>*/

    @Insert
    suspend fun insertUserCourseCrossRef(crossRef: List<EstudianteCursoCrossRef>)
}