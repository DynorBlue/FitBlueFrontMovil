package org.utl.fitblueapp.ui.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.utl.fitblueapp.db.entity.Categoria

@Composable
fun AgregarCategoriaDialog(
    onDismiss: () -> Unit,
    onConfirm: (nombre: String) -> Unit
) {
    var nombre by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar categoría") },
        text = {
            Column {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(nombre.trim())
                    onDismiss()
                },
                enabled = nombre.isNotBlank()
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}


@Composable
fun EditarCategoriaDialog(
    categoria: Categoria,
    onDismiss: () -> Unit,
    onSave: (Categoria) -> Unit
) {
    var nombre by remember { mutableStateOf(categoria.nombre) }
    val nombreTrim = nombre.trim()
    val hasChanges = nombreTrim != categoria.nombre.trim()
    val canSave = nombreTrim.isNotEmpty() && hasChanges

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Editar categoría", style = MaterialTheme.typography.titleMedium) },
        text = {
            Column {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val categoriaActualizada = categoria.copy(nombre = nombreTrim)
                    onSave(categoriaActualizada)
                    onDismiss()
                },
                enabled = canSave
            ) {
                Text("Guardar cambios")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun EliminarCategoriaDialog(
    categoria: Categoria,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Eliminar categoría") },
        text = {
            Text("¿Estás seguro de que deseas eliminar la categoría \"${categoria.nombre}\"? Esta acción no se puede deshacer.")
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    onDismiss()
                }
            ) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
