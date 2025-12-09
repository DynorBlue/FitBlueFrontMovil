package org.utl.fitblueapp.ui.components.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items

import org.utl.fitblueapp.db.entity.Sesion
import org.utl.fitblueapp.ui.components.cards.SesionCard

@Composable
fun SesionList(
    sesiones: List<Sesion>,
    onClick: (Sesion) -> Unit
) {
    LazyColumn {
        items(sesiones) { sesion ->
            SesionCard(sesion = sesion, onClick = { onClick(sesion) })
        }
    }
}