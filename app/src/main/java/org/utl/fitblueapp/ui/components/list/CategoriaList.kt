package org.utl.fitblueapp.ui.components.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import org.utl.fitblueapp.db.entity.Categoria
import org.utl.fitblueapp.ui.components.cards.CategoriaCard

@Composable
fun CategoriaList(
    categorias: List<Categoria>,
    onClick: (Categoria) -> Unit
){
    LazyColumn {
        items(categorias){
            categoria ->
            CategoriaCard(categoria = categoria, onClick = {onClick(categoria)})
        }
    }
}