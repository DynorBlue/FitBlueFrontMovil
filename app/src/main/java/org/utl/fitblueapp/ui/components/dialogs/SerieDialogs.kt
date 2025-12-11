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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.utl.fitblueapp.db.entity.Serie

@Composable
fun AgregarSerieDialog(
    onDismiss: () -> Unit,
    onConfirm: (sesionId: Long, peso: Double, repeticiones: Int) -> Unit
) {
    var sesionId by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var repeticiones by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Serie") },
        text = {
            Column {
                OutlinedTextField(
                    value = sesionId,
                    onValueChange = { sesionId = it },
                    label = { Text("ID Sesión") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    isError = sesionId.isBlank() && errorMensaje.isNotEmpty()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = peso,
                    onValueChange = { peso = it },
                    label = { Text("Peso (kg)") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next
                    ),
                    isError = peso.isBlank() && errorMensaje.isNotEmpty()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = repeticiones,
                    onValueChange = { repeticiones = it },
                    label = { Text("Repeticiones") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    isError = repeticiones.isBlank() && errorMensaje.isNotEmpty()
                )
                if (errorMensaje.isNotEmpty()) {
                    Text(
                        text = errorMensaje,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val sesionIdLong = sesionId.toLongOrNull()
                    val pesoDouble = peso.toDoubleOrNull()
                    val repeticionesInt = repeticiones.toIntOrNull()
                    
                    when {
                        sesionId.isBlank() -> errorMensaje = "El ID de sesión es requerido"
                        sesionIdLong == null -> errorMensaje = "El ID de sesión debe ser un número válido"
                        sesionIdLong <= 0 -> errorMensaje = "El ID de sesión debe ser mayor a 0"
                        peso.isBlank() -> errorMensaje = "El peso es requerido"
                        pesoDouble == null -> errorMensaje = "El peso debe ser un número válido"
                        pesoDouble <= 0 -> errorMensaje = "El peso debe ser mayor a 0"
                        repeticiones.isBlank() -> errorMensaje = "Las repeticiones son requeridas"
                        repeticionesInt == null -> errorMensaje = "Las repeticiones deben ser un número válido"
                        repeticionesInt <= 0 -> errorMensaje = "Las repeticiones deben ser mayor a 0"
                        else -> {
                            onConfirm(sesionIdLong, pesoDouble, repeticionesInt)
                            onDismiss()
                        }
                    }
                }
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
fun EditarSerieDialog(
    serie: Serie,
    onDismiss: () -> Unit,
    onSave: (Serie) -> Unit
) {
    var sesionId by remember { mutableStateOf(serie.sesionId.toString()) }
    var peso by remember { mutableStateOf(serie.peso.toString()) }
    var repeticiones by remember { mutableStateOf(serie.repeticiones.toString()) }
    var errorMensaje by remember { mutableStateOf("") }

    val sesionIdLong = sesionId.trim().toLongOrNull()
    val pesoDouble = peso.trim().toDoubleOrNull()
    val repeticionesInt = repeticiones.trim().toIntOrNull()
    
    val hasChanges = (sesionIdLong != serie.sesionId || 
                     pesoDouble != serie.peso || 
                     repeticionesInt != serie.repeticiones)
    val canSave = (sesionIdLong != null && sesionIdLong > 0 &&
                  pesoDouble != null && pesoDouble > 0 &&
                  repeticionesInt != null && repeticionesInt > 0 &&
                  hasChanges)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Editar Serie", style = MaterialTheme.typography.titleMedium) },
        text = {
            Column {
                OutlinedTextField(
                    value = sesionId,
                    onValueChange = { sesionId = it },
                    label = { Text("ID Sesión") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    isError = sesionId.isBlank() && errorMensaje.isNotEmpty()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = peso,
                    onValueChange = { peso = it },
                    label = { Text("Peso (kg)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next
                    ),
                    isError = peso.isBlank() && errorMensaje.isNotEmpty()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = repeticiones,
                    onValueChange = { repeticiones = it },
                    label = { Text("Repeticiones") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    isError = repeticiones.isBlank() && errorMensaje.isNotEmpty()
                )
                if (errorMensaje.isNotEmpty()) {
                    Text(
                        text = errorMensaje,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    when {
                        sesionId.isBlank() -> errorMensaje = "El ID de sesión es requerido"
                        sesionIdLong == null -> errorMensaje = "El ID de sesión debe ser un número válido"
                        sesionIdLong <= 0 -> errorMensaje = "El ID de sesión debe ser mayor a 0"
                        peso.isBlank() -> errorMensaje = "El peso es requerido"
                        pesoDouble == null -> errorMensaje = "El peso debe ser un número válido"
                        pesoDouble <= 0 -> errorMensaje = "El peso debe ser mayor a 0"
                        repeticiones.isBlank() -> errorMensaje = "Las repeticiones son requeridas"
                        repeticionesInt == null -> errorMensaje = "Las repeticiones deben ser un número válido"
                        repeticionesInt <= 0 -> errorMensaje = "Las repeticiones deben ser mayor a 0"
                        !hasChanges -> errorMensaje = "No hay cambios para guardar"
                        else -> {
                            val serieActualizada = serie.copy(
                                sesionId = sesionIdLong,
                                peso = pesoDouble,
                                repeticiones = repeticionesInt
                            )
                            onSave(serieActualizada)
                            onDismiss()
                        }
                    }
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