package com.example.danp_lab04.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.danp_lab04.entities.CursoEntity
import com.example.danp_lab04.entities.EstudianteCursoCrossRef
import com.example.danp_lab04.entities.EstudianteEntity


@Database(
    entities = [EstudianteEntity::class, CursoEntity::class, EstudianteCursoCrossRef::class],
    version = 6
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun estudianteDao(): EstudianteDao
    abstract fun cursoDao(): CursoDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database-name"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance

                }

                return instance
            }
        }
    }
}