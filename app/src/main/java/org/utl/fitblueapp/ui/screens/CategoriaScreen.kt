package org.utl.fitblueapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import org.utl.fitblueapp.db.entity.Categoria
import org.utl.fitblueapp.db.viewModel.CategoriaViewModel
import org.utl.fitblueapp.ui.components.buttons.BotonAgregar
import org.utl.fitblueapp.ui.components.dialogs.AgregarCategoriaDialog
import org.utl.fitblueapp.ui.components.dialogs.EditarCategoriaDialog
import org.utl.fitblueapp.ui.components.dialogs.EliminarCategoriaDialog
import org.utl.fitblueapp.ui.components.list.CategoriaList
import org.utl.fitblueapp.ui.theme.blanco
import org.utl.fitblueapp.ui.theme.negro

@Composable
fun CategoriaScreen(viewModel: CategoriaViewModel){
    val categorias by viewModel.categorias.collectAsState()
    val uiState by viewModel.uiState
    
    var showAgregarDialog by remember { mutableStateOf(false) }
    var showEditarDialog by remember { mutableStateOf(false) }
    var showEliminarDialog by remember { mutableStateOf(false) }
    var categoriaSeleccionada by remember { mutableStateOf<Categoria?>(null) }
    
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
            text = "CATEGORIAS",
            fontSize = 35.sp,
            fontWeight = FontWeight.ExtraBold,
            color = blanco,
           modifier = Modifier.padding(25.dp)
        )
        CategoriaList(
            categorias = categorias,
            onClick = { categoria -> 
                println("Clic en categoría: ${categoria.nombre}")
            },
            onEditar = { categoria ->
                categoriaSeleccionada = categoria
                showEditarDialog = true
            },
            onEliminar = { categoria ->
                categoriaSeleccionada = categoria
                showEliminarDialog = true
            }
        )
        
        BotonAgregar("Agregar categoria", onClick = {
            showAgregarDialog = true
        })
    }
    
    if (showAgregarDialog) {
        AgregarCategoriaDialog(
            onDismiss = { showAgregarDialog = false },
            onConfirm = { nombre ->
                viewModel.agregarCategoria(nombre)
            }
        )
    }
    
    if (showEditarDialog && categoriaSeleccionada != null) {
        EditarCategoriaDialog(
            categoria = categoriaSeleccionada!!,
            onDismiss = { 
                showEditarDialog = false
                categoriaSeleccionada = null
            },
            onSave = { categoriaActualizada ->
                viewModel.actualizarCategoria(categoriaActualizada)
            }
        )
    }
    
    if (showEliminarDialog && categoriaSeleccionada != null) {
        EliminarCategoriaDialog(
            categoria = categoriaSeleccionada!!,
            onDismiss = { 
                showEliminarDialog = false
                categoriaSeleccionada = null
            },
            onConfirm = {
                viewModel.eliminarCategoria(categoriaSeleccionada!!)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriaScreenPreview() {
    val categoriasDePrueba = listOf(
        Categoria(idCategoria = 1, nombre = "Pecho"),
        Categoria(idCategoria = 2, nombre = "Espalda"),
        Categoria(idCategoria = 3, nombre = "Piernas"),
        Categoria(idCategoria = 4, nombre = "Brazos"),
        Categoria(idCategoria = 5, nombre = "Abdomen")
    )
    
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = negro)
        .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "CATEGORIAS",
            fontSize = 35.sp,
            fontWeight = FontWeight.ExtraBold,
            color = blanco,
            modifier = Modifier.padding(25.dp)
        )
        
        CategoriaList(
            categorias = categoriasDePrueba,
            onClick = { categoria -> 
                println("Clic en categoría: ${categoria.nombre}")
            }
        )
        
        BotonAgregar("Agregar categoria", onClick = {})
    }
}