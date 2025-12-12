package org.utl.fitblueapp.ui.components.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.utl.fitblueapp.db.entity.Serie
import org.utl.fitblueapp.ui.components.cards.SerieCard

@Composable
fun SerieList(
    modifier: Modifier = Modifier,
    series: List<Serie>,
    onClick: (Serie) -> Unit,
    onEditar: (Serie) -> Unit = {},
    onEliminar: (Serie) -> Unit = {}
){
    LazyColumn(modifier = modifier) {
        items(series){
            serie ->
            SerieCard(
                serie = serie, 
                onclick = {onClick(serie)},
                onEditar = onEditar,
                onEliminar = onEliminar
            )
        }
    }
}