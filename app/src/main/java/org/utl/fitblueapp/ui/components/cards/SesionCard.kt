package org.utl.fitblueapp.ui.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import org.utl.fitblueapp.db.entity.Sesion
import org.utl.fitblueapp.ui.components.buttons.BotonEditar
import org.utl.fitblueapp.ui.components.buttons.BotonEliminar
import org.utl.fitblueapp.ui.theme.azulFit
import org.utl.fitblueapp.ui.theme.blanco

@Composable
fun SesionCard(sesion: Sesion, onClick: () -> Unit,
               onEditar: (Sesion) -> Unit = {},
               onEliminar: (Sesion) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = blanco),
        colors = CardDefaults.cardColors(containerColor = azulFit),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "SESION:",
                color = blanco,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "ID: ${sesion.idSesion}",
                color = blanco,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Número Sesión: ${sesion.numeroSesion}",
                color = blanco,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Fecha: ${sesion.fecha}",
                color = blanco,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "ID Ejercicio: ${sesion.ejercicioId}",
                color = blanco,
                style = MaterialTheme.typography.bodyMedium
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BotonEditar(
                    texto = "Editar",
                    onClick = {onEditar(sesion)},
                    modifier = Modifier.weight(1f)
                )
                BotonEliminar(
                    texto = "Borrar",
                    onClick = {onEliminar(sesion)},
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}