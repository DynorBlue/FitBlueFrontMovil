package org.utl.fitblueapp.ui.components.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier

import org.utl.fitblueapp.db.entity.Sesion
import org.utl.fitblueapp.ui.components.cards.SesionCard

@Composable
fun SesionList(
    modifier: Modifier = Modifier,
    sesiones: List<Sesion>,
    onClick: (Sesion) -> Unit,
    onEditar: (Sesion) -> Unit = {},
    onEliminar: (Sesion) -> Unit = {}
) {
    LazyColumn(modifier = modifier) {
        items(sesiones) { sesion ->
            SesionCard(
                sesion = sesion, 
                onClick = { onClick(sesion) },
                onEditar = onEditar,
                onEliminar = onEliminar
            )
        }
    }
}