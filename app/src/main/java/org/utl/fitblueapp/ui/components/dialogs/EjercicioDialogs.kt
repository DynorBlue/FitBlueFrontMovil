package org.utl.fitblueapp.ui.components.dialogs

import androidx.compose.material3.AlertDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.utl.fitblueapp.db.entity.Ejercicio
import org.utl.fitblueapp.ui.theme.rojoFit

@Composable
fun AgregarEjercicioDialog(
    onDismiss: () -> Unit,
    onConfirm: (nombreEjercicio: String, descripcion: String, categoriaId: Long) -> Unit
) {

    var nombreEjercicio by remember { mutableStateOf("") }

    var descripcion by remember { mutableStateOf("") }
    var categoriaId by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Ejercicio") },
        text = {
            Column {
                OutlinedTextField(
                    value = nombreEjercicio,
                    onValueChange = { nombreEjercicio = it },
                    label = { Text("Nombre Ejercicio") },
                    isError = nombreEjercicio.isBlank() && errorMensaje.isNotEmpty()
                )
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripcion ejercicio") },
                    isError = descripcion.isBlank() && errorMensaje.isNotEmpty()
                )
                OutlinedTextField(
                    value = categoriaId,
                    onValueChange = { categoriaId = it },
                    label = { Text("Id categoria") },
                    isError = categoriaId.toLongOrNull() == null && errorMensaje.isNotEmpty()
                )
                if (errorMensaje.isNotEmpty()) {
                    Text(
                        text = errorMensaje,
                        color = rojoFit
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val categoriaIdlong = categoriaId.toLongOrNull()
                    if (nombreEjercicio.isBlank()) {
                        errorMensaje = "El nombre del ejercicio es requerido"
                    } else if (categoriaIdlong == null) {
                        errorMensaje = "El id de la Categoria deberia ser valido"
                    } else if (categoriaIdlong <= 0) {
                        errorMensaje = "El id categoria debe ser mayor a 0"
                    } else if (descripcion.isBlank()) {
                        errorMensaje = "La descripcion del ejercicio es requerida"
                    } else {
                        onConfirm(nombreEjercicio, descripcion, categoriaIdlong)
                    }
                }
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun EditarEjercicioDialog(
    ejercicio: Ejercicio,
    onDismiss: () -> Unit,
    onConfirm: (Ejercicio) -> Unit
) {
    var nombreEjercicio by remember { mutableStateOf(ejercicio.nombreEjercicio) }
    // descripcion es nullable en la entidad, pero aquí la manejamos como String
    var descripcionText by remember { mutableStateOf(ejercicio.descripcion ?: "") }
    var categoriaIdText by remember { mutableStateOf(ejercicio.categoriaId.toString()) }
    var errorMensaje by remember { mutableStateOf("") }

    // helpers
    val nombreTrim = nombreEjercicio.trim()
    val categoriaIdLong = categoriaIdText.trim().toLongOrNull()
    val canSave = nombreTrim.isNotEmpty() && (categoriaIdLong != null && categoriaIdLong > 0)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Ejercicio") },
        text = {
            Column {
                OutlinedTextField(
                    value = nombreEjercicio,
                    onValueChange = { nombreEjercicio = it },
                    label = { Text("Nombre del ejercicio") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    isError = nombreEjercicio.isBlank() && errorMensaje.isNotEmpty()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = descripcionText,
                    onValueChange = { descripcionText = it },
                    label = { Text("Descripción (opcional)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    isError = false
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = categoriaIdText,
                    onValueChange = { categoriaIdText = it },
                    label = { Text("Id de la categoría") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    isError = (categoriaIdText.trim().toLongOrNull() == null) && errorMensaje.isNotEmpty()
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (errorMensaje.isNotEmpty()) {
                    Text(
                        text = errorMensaje,
                        color = androidx.compose.ui.graphics.Color.Red
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    // validaciones
                    val catId = categoriaIdText.trim().toLongOrNull()
                    when {
                        nombreTrim.isEmpty() -> {
                            errorMensaje = "El nombre del ejercicio es requerido"
                        }
                        catId == null -> {
                            errorMensaje = "El id de la categoría debe ser un número válido"
                        }
                        catId <= 0L -> {
                            errorMensaje = "El id de la categoría debe ser mayor a 0"
                        }
                        else -> {
                            // convertir descripcion a nullable
                            val descripcionNullable: String? = descripcionText.trim().ifEmpty { null }

                            val actualizado = ejercicio.copy(
                                nombreEjercicio = nombreTrim,
                                descripcion = descripcionNullable,
                                categoriaId = catId
                            )
                            onConfirm(actualizado)
                            onDismiss()
                        }
                    }
                },
                enabled = true
            ) {
                Text("Guardar cambios")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}