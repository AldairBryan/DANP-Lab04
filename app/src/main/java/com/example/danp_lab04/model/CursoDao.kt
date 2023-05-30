package com.example.danp_lab04.model

import androidx.room.*
import com.example.danp_lab04.entities.CursoConEstudiante
import com.example.danp_lab04.entities.CursoEntity

@Dao
interface CursoDao {
    @Query("select * from curso")
    suspend fun getAll(): List<CursoEntity>

    @Insert
    suspend fun insert(courseEntity: CursoEntity)

    @Insert
    suspend fun insert(coursesEntity: List<CursoEntity>)

    @Delete
    suspend fun delete(courseEntity: CursoEntity)

    @Transaction
    @Query("select * from curso")
    suspend fun getCoursesWithStudents(): List<CursoConEstudiante>

}