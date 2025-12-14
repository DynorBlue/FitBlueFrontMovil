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
import org.utl.fitblueapp.db.entity.Sesion
import org.utl.fitblueapp.db.viewModel.SesionViewModel
import org.utl.fitblueapp.ui.components.buttons.BotonAgregar
import org.utl.fitblueapp.ui.components.dialogs.AgregarSesionDialog
import org.utl.fitblueapp.ui.components.dialogs.EditarSesionDialog
import org.utl.fitblueapp.ui.components.dialogs.EliminarSesionDialog
import org.utl.fitblueapp.ui.components.list.SesionList
import org.utl.fitblueapp.ui.theme.blanco
import org.utl.fitblueapp.ui.theme.negro
import java.util.Date

@ExperimentalCoroutinesApi
@Composable
fun SesionScreen(
    viewModel: SesionViewModel, 
    ejercicioId: Long = 1L,
    onSesionClick: (Sesion) -> Unit = {}
){
    val sesiones by viewModel.sesionesPorEjercicio.collectAsState()
    val uiState by viewModel.uiState
    
    var showAgregarDialog by remember { mutableStateOf(false) }
    var showEditarDialog by remember { mutableStateOf(false) }
    var showEliminarDialog by remember { mutableStateOf(false) }
    var sesionSeleccionada by remember { mutableStateOf<Sesion?>(null) }
    
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
            text = "SESIONES",
            fontSize = 35.sp,
            fontWeight = FontWeight.ExtraBold,
            color = blanco,
            modifier = Modifier.padding(25.dp)
        )
        SesionList(
            modifier = Modifier.weight(1f),
            sesiones = sesiones,
            onClick = onSesionClick,
            onEditar = { sesion ->
                sesionSeleccionada = sesion
                showEditarDialog = true
            },
            onEliminar = { sesion ->
                sesionSeleccionada = sesion
                showEliminarDialog = true
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        BotonAgregar("Agregar sesion", onClick = {
            showAgregarDialog = true
        })
    }
    
    if (showAgregarDialog) {
        AgregarSesionDialog(
            onDismiss = { showAgregarDialog = false },
            onConfirm = { numeroSesion, fecha, ejercicioId ->
                viewModel.agregarSesion(numeroSesion, fecha, ejercicioId)
            }
        )
    }
    
    if (showEditarDialog && sesionSeleccionada != null) {
        EditarSesionDialog(
            sesion = sesionSeleccionada!!,
            onDismiss = { 
                showEditarDialog = false
                sesionSeleccionada = null
            },
            onSave = { sesionActualizada ->
                viewModel.actualizarSesion(sesionActualizada)
            }
        )
    }
    
    if (showEliminarDialog && sesionSeleccionada != null) {
        EliminarSesionDialog(
            sesion = sesionSeleccionada!!,
            onDismiss = { 
                showEliminarDialog = false
                sesionSeleccionada = null
            },
            onConfirm = {
                viewModel.eliminarSesion(sesionSeleccionada!!)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SesionScreenPreview() {
    val sesionesDePrueba = listOf(
        Sesion(idSesion = 1, numeroSesion = 1, fecha = Date(), ejercicioId = 1),
        Sesion(idSesion = 2, numeroSesion = 2, fecha = Date(), ejercicioId = 2),
        Sesion(idSesion = 3, numeroSesion = 3, fecha = Date(), ejercicioId = 3),
        Sesion(idSesion = 4, numeroSesion = 4, fecha = Date(), ejercicioId = 4),
        Sesion(idSesion = 5, numeroSesion = 5, fecha = Date(), ejercicioId = 5)
    )
    
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = negro)
        .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "SESIONES",
            fontSize = 35.sp,
            fontWeight = FontWeight.ExtraBold,
            color = blanco,
            modifier = Modifier.padding(25.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        SesionList(
            modifier = Modifier.weight(1f),
            sesiones = sesionesDePrueba,
            onClick = {} // Empty click for preview
        )
        Spacer(modifier = Modifier.height(16.dp))
        BotonAgregar("Agregar sesion", onClick = {})
    }
}
