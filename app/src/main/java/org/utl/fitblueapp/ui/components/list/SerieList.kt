package org.utl.fitblueapp.ui.components.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import org.utl.fitblueapp.db.entity.Serie
import org.utl.fitblueapp.ui.components.cards.SerieCard

@Composable
fun SerieList(
    series: List<Serie>,
    onClick: (Serie) -> Unit
){
    LazyColumn {
        items(series){
            serie ->
            SerieCard(serie = serie, onclick = {onClick(serie)})
        }
    }
}