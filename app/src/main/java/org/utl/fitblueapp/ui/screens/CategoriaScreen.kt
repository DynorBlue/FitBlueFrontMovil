package org.utl.fitblueapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import org.utl.fitblueapp.db.entity.Categoria
import org.utl.fitblueapp.ui.components.buttons.BotonAgregar
import org.utl.fitblueapp.ui.components.list.CategoriaList
import org.utl.fitblueapp.ui.theme.blanco
import org.utl.fitblueapp.ui.theme.negro

@Composable
fun CategoriaScreen(){
    Column(modifier = Modifier
        .fillMaxSize() // Ocupa todo el espacio disponible
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
            categorias = emptyList(),
            onClick = { categoria -> 
                println("Clic en categoría: ${categoria.nombre}")
            }
        )
        
        //agregar Categoria
        BotonAgregar("Agregar categoria", onClick = {})
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