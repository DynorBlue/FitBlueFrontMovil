package org.utl.fitblueapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import org.utl.fitblueapp.db.entity.Ejercicio
import org.utl.fitblueapp.db.viewModel.EjercicioViewModel
import org.utl.fitblueapp.ui.components.buttons.BotonAgregar
import org.utl.fitblueapp.ui.components.dialogs.AgregarEjercicioDialog
import org.utl.fitblueapp.ui.components.dialogs.EditarEjercicioDialog
import org.utl.fitblueapp.ui.components.dialogs.EliminarEjercicioDialog
import org.utl.fitblueapp.ui.components.list.EjercicioList
import org.utl.fitblueapp.ui.theme.blanco
import org.utl.fitblueapp.ui.theme.negro

@ExperimentalCoroutinesApi
@Composable
fun EjercicioScreen(
    viewModel: EjercicioViewModel, 
    categoriaId: Long = 1L,
    onEjercicioClick: (Ejercicio) -> Unit = {}
){
    val ejercicios by viewModel.ejerciciosPorCategoria.collectAsState()
    val uiState by viewModel.uiState
    
    var showAgregarDialog by remember { mutableStateOf(false) }
    var showEditarDialog by remember { mutableStateOf(false) }
    var showEliminarDialog by remember { mutableStateOf(false) }
    var ejercicioSeleccionado by remember { mutableStateOf<Ejercicio?>(null) }
    
    LaunchedEffect(uiState.mensaje) {
        if (uiState.mensaje != null) {
            viewModel.limpiarMensaje()
        }
    }
    
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = negro)
        .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "EJERCICIOS",
            fontSize = 35.sp,
            fontWeight = FontWeight.ExtraBold,
            color = blanco,
            modifier = Modifier.padding(25.dp)
        )
        EjercicioList(
            modifier = Modifier.weight(1f),
            ejercicios = ejercicios,
            onClick = onEjercicioClick,
            onEditar = { ejercicio ->
                ejercicioSeleccionado = ejercicio
                showEditarDialog = true
            },
            onEliminar = { ejercicio ->
                ejercicioSeleccionado = ejercicio
                showEliminarDialog = true
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        BotonAgregar("Agregar ejercicio", onClick = {
            showAgregarDialog = true
        })
    }
    
    if (showAgregarDialog) {
        AgregarEjercicioDialog(
            onDismiss = { showAgregarDialog = false },
            onConfirm = { nombre, descripcion, categoriaId ->
                viewModel.agregarEjercicio(nombre, descripcion, categoriaId)
            }
        )
    }
    
    if (showEditarDialog && ejercicioSeleccionado != null) {
        EditarEjercicioDialog(
            ejercicio = ejercicioSeleccionado!!,
            onDismiss = { 
                showEditarDialog = false
                ejercicioSeleccionado = null
            },
            onConfirm = { ejercicioActualizado ->
                viewModel.actualizarEjercicio(ejercicioActualizado)
            }
        )
    }
    
    if (showEliminarDialog && ejercicioSeleccionado != null) {
        EliminarEjercicioDialog(
            ejercicio = ejercicioSeleccionado!!,
            onDismiss = { 
                showEliminarDialog = false
                ejercicioSeleccionado = null
            },
            onConfirm = {
                viewModel.eliminarEjercicio(ejercicioSeleccionado!!)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EjercicioScreenPreview() {
    val ejerciciosDePrueba = listOf(
        Ejercicio(idEjercicio = 1, nombreEjercicio = "Press de Banca", descripcion = "Ejercicio para pecho", categoriaId = 1),
        Ejercicio(idEjercicio = 2, nombreEjercicio = "Sentadilla", descripcion = "Ejercicio para piernas", categoriaId = 3),
        Ejercicio(idEjercicio = 3, nombreEjercicio = "Remo con Barra", descripcion = "Ejercicio para espalda", categoriaId = 2),
        Ejercicio(idEjercicio = 4, nombreEjercicio = "Curl de Bíceps", descripcion = "Ejercicio para brazos", categoriaId = 4),
        Ejercicio(idEjercicio = 5, nombreEjercicio = "Plancha Abdominal", descripcion = "Ejercicio para abdomen", categoriaId = 5)
    )
    
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = negro)
        .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "EJERCICIOS",
            fontSize = 35.sp,
            fontWeight = FontWeight.ExtraBold,
            color = blanco,
            modifier = Modifier.padding(25.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        EjercicioList(
            modifier = Modifier.weight(1f),
            ejercicios = ejerciciosDePrueba,
            onClick = { ejercicio ->
                println("Clic en ejercicio: ${ejercicio.nombreEjercicio}")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        BotonAgregar("Agregar ejercicio", onClick = {})
    }
}
