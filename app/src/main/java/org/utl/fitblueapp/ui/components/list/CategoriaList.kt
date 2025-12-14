package org.utl.fitblueapp.ui.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import org.utl.fitblueapp.db.entity.Categoria
import org.utl.fitblueapp.ui.components.cards.CategoriaCard

@Composable
fun CategoriaList(
    categorias: List<Categoria>,
    onClick: (Categoria) -> Unit,
    onEditar: (Categoria) -> Unit = {},
    onEliminar: (Categoria) -> Unit = {}
){
LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(categorias){
            categoria ->
            CategoriaCard(
                categoria = categoria, 
                onClick = {onClick(categoria)},
                onEditar = onEditar,
                onEliminar = onEliminar
            )
        }
    }
}