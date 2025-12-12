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
import org.utl.fitblueapp.db.entity.Serie
import org.utl.fitblueapp.ui.components.buttons.BotonAgregar
import org.utl.fitblueapp.ui.components.list.SerieList
import org.utl.fitblueapp.ui.theme.blanco
import org.utl.fitblueapp.ui.theme.negro

@Composable
fun SerieScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = negro)
        .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "SERIES",
            fontSize = 35.sp,
            fontWeight = FontWeight.ExtraBold,
            color = blanco,
            modifier = Modifier.padding(25.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        SerieList(
            modifier = Modifier.weight(1f),
            series = emptyList(),
            onClick = { serie -> 
                println("Clic en serie: ${serie.idSerie}")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        BotonAgregar("Agregar serie", onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun SerieScreenPreview() {
    val seriesDePrueba = listOf(
        Serie(idSerie = 1, sesionId = 1, peso = 60.0, repeticiones = 12),
        Serie(idSerie = 2, sesionId = 1, peso = 65.0, repeticiones = 10),
        Serie(idSerie = 3, sesionId = 2, peso = 80.0, repeticiones = 8),
        Serie(idSerie = 4, sesionId = 2, peso = 85.0, repeticiones = 6),
        Serie(idSerie = 5, sesionId = 3, peso = 50.0, repeticiones = 15)
    )
    
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = negro)
        .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "SERIES",
            fontSize = 35.sp,
            fontWeight = FontWeight.ExtraBold,
            color = blanco,
            modifier = Modifier.padding(25.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        SerieList(
            modifier = Modifier.weight(1f),
            series = seriesDePrueba,
            onClick = { serie -> 
                println("Clic en serie: ${serie.idSerie}")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        BotonAgregar("Agregar serie", onClick = {})
    }
}
