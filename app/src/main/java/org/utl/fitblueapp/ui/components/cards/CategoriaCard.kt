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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.utl.fitblueapp.db.entity.Categoria
import org.utl.fitblueapp.ui.components.buttons.BotonEditar
import org.utl.fitblueapp.ui.components.buttons.BotonEliminar
import org.utl.fitblueapp.ui.theme.azulFit
import org.utl.fitblueapp.ui.theme.blanco


@Composable
fun CategoriaCard(
    categoria: Categoria, 
    onClick: () -> Unit,
    onEditar: (Categoria) -> Unit = {},
    onEliminar: (Categoria) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(300.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = blanco),
        colors = CardDefaults.cardColors(containerColor = azulFit),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "CATEGORIA:",
                color = blanco,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "${categoria.nombre}",
                color = blanco,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "ID: ${categoria.idCategoria}",
                color = blanco,
                style = MaterialTheme.typography.titleMedium
            )
            
            Spacer(modifier = Modifier.height(50.dp))
            
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BotonEditar(
                    texto = "Editar",
                    onClick = { onEditar(categoria) },
                    modifier = Modifier.weight(1f)
                )
                BotonEliminar(
                    texto = "Eliminar", 
                    onClick = { onEliminar(categoria) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}