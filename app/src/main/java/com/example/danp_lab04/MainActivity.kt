package com.example.danp_lab04

import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.danp_lab04.entities.CursoConEstudiante
import com.example.danp_lab04.entities.CursoEntity
import com.example.danp_lab04.entities.EstudianteEntity
import com.example.danp_lab04.model.AppDatabase
import com.example.danp_lab04.model.Repository
import com.example.danp_lab04.ui.theme.DANPLab04Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val coroutineScope = CoroutineScope((Dispatchers.Default))

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            DANPLab04Theme {
                val screenState = remember { mutableStateOf("FILL") }
                // A surface container using the 'background' color from the theme
                val coursestudents = remember { mutableStateOf<List<CursoConEstudiante>?>(null) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val context = LocalContext.current
                    val repository = Repository(AppDatabase.getInstance(context.applicationContext))
                    val scope = rememberCoroutineScope()



                    if(screenState.value == "FILL") {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = { fillTables(repository, scope) }) {
                                Text(text = "Fill Tables")
                            }

                            Button(onClick = { screenState.value = "VISUAL" }) {
                                Text(text = "Visualize")
                            }
                        }
                    }
                    else {
                        LaunchedEffect(Unit) {
                            coroutineScope.launch {
                                val list = repository.getCursoConEstudiantes()
                                coursestudents.value = list
                            }
                        }
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = { screenState.value = "FILL" }) {
                                Text(text = "Back")
                            }
                            if (coursestudents.value != null) {
                                VisulizeScreen(coursestudents = coursestudents.value!!)
                            }
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun VisulizeScreen(coursestudents: List<CursoConEstudiante>) {
        LazyColumn(contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)) {
            items(coursestudents) { courseStudent ->
                Spacer(modifier = Modifier.height(12.dp))
                Card(modifier = Color.Magenta) {
                    Box(Modifier.padding(10.dp)) {
                        Text(text = courseStudent.curso.nombre)
                        Column(Modifier.padding(top = 26.dp)) {
                            courseStudent.students.map { student ->
                                Text(text = student.fullname)
                            }
                        }
                    }
                }
            }
        }
    }
}


fun fillTables(rep: Repository, scope: CoroutineScope) {
    val courses = ArrayList<CursoEntity>()
    val students = ArrayList<EstudianteEntity>()

    for (i in 20..29) {
        students.add(EstudianteEntity(i, fullname = "Name $i ${i+3}"))
    }

    scope.launch {
        rep.insertEstudiantes(students)
    }

    for (i in 1..6) {
        courses.add(CursoEntity(i, nombre = "Course $i - Name"))
    }
    scope.launch {
        rep.insertCursos(courses)
    }

    for( i in students ) {
        for (j in 1..3) {
            scope.launch {
                rep.insertEstudianteConCurso(i, courses[j])
            }
        }
    }
}