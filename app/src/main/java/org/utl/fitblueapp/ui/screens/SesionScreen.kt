package org.utl.fitblueapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import org.utl.fitblueapp.db.entity.Sesion
import org.utl.fitblueapp.ui.components.buttons.BotonAgregar
import org.utl.fitblueapp.ui.components.list.SesionList
import org.utl.fitblueapp.ui.theme.blanco
import org.utl.fitblueapp.ui.theme.negro
import java.util.Date

@Composable
fun SesionScreen(){
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
            sesiones = emptyList(),
            onClick = { sesion -> 
                println("Clic en sesion: ${sesion.idSesion}")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        BotonAgregar("Agregar sesion", onClick = {})
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
            onClick = { sesion -> 
                println("Clic en sesion: ${sesion.idSesion}")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        BotonAgregar("Agregar sesion", onClick = {})
    }
}
