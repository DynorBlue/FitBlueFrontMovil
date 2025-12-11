package org.utl.fitblueapp.ui.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.utl.fitblueapp.db.entity.Ejercicio
import org.utl.fitblueapp.ui.theme.azulFit
import org.utl.fitblueapp.ui.theme.blanco

@Composable
fun EjercicioCard(ejercicio: Ejercicio, onClick: () -> Unit){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = blanco),
        colors = CardDefaults.cardColors(
            containerColor = azulFit
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Column (modifier = Modifier.padding(16.dp)){
            Text(
                text = "EJERCICIO:",
                color = blanco,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "${ejercicio.nombreEjercicio}",
                color = blanco,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}