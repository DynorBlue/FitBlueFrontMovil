package org.utl.fitblueapp.ui.components.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.utl.fitblueapp.db.entity.Ejercicio
import org.utl.fitblueapp.ui.components.cards.EjercicioCard

@Composable
fun EjercicioList(
    modifier: Modifier = Modifier,
    ejercicios: List<Ejercicio>,
    onClick: (Ejercicio) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(ejercicios) { ejercicio ->
            EjercicioCard(ejercicio = ejercicio, onClick = { onClick(ejercicio) })
        }
    }
}