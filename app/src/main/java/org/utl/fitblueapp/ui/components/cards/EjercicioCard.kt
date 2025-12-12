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
import org.utl.fitblueapp.ui.components.buttons.BotonEditar
import org.utl.fitblueapp.ui.components.buttons.BotonEliminar
import org.utl.fitblueapp.ui.theme.azulFit
import org.utl.fitblueapp.ui.theme.blanco

@Composable
fun EjercicioCard(ejercicio: Ejercicio, onClick: () -> Unit){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
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
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Nombre: ${ejercicio.nombreEjercicio}",
                color = blanco,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "ID: ${ejercicio.idEjercicio}",
                color = blanco,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Descripción: ${ejercicio.descripcion ?: "Sin descripción"}",
                color = blanco,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "ID Categoría: ${ejercicio.categoriaId}",
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
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
                BotonEliminar(
                    texto = "Borrar",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}