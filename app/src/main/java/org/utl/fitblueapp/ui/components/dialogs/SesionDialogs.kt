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
import org.utl.fitblueapp.db.entity.Sesion
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AgregarSesionDialog(
    onDismiss: () -> Unit,
    onConfirm: (numeroSesion: Long, fecha: Date, ejercicioId: Long) -> Unit
) {
    var numeroSesion by remember { mutableStateOf("") }
    var fechaTexto by remember { mutableStateOf("") }
    var ejercicioId by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Sesión") },
        text = {
            Column {
                OutlinedTextField(
                    value = numeroSesion,
                    onValueChange = { numeroSesion = it },
                    label = { Text("Número de Sesión") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    isError = numeroSesion.isBlank() && errorMensaje.isNotEmpty()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = fechaTexto,
                    onValueChange = { fechaTexto = it },
                    label = { Text("Fecha (dd/MM/yyyy)") },
                    placeholder = { Text("Ej: 11/12/2025") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    isError = fechaTexto.isBlank() && errorMensaje.isNotEmpty()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = ejercicioId,
                    onValueChange = { ejercicioId = it },
                    label = { Text("ID Ejercicio") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    isError = ejercicioId.isBlank() && errorMensaje.isNotEmpty()
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
                    val numeroSesionLong = numeroSesion.toLongOrNull()
                    val ejercicioIdLong = ejercicioId.toLongOrNull()
                    
                    when {
                        numeroSesion.isBlank() -> errorMensaje = "El número de sesión es requerido"
                        numeroSesionLong == null -> errorMensaje = "El número de sesión debe ser un número válido"
                        numeroSesionLong <= 0 -> errorMensaje = "El número de sesión debe ser mayor a 0"
                        fechaTexto.isBlank() -> errorMensaje = "La fecha es requerida"
                        !isValidDate(fechaTexto) -> errorMensaje = "Formato de fecha inválido. Use dd/MM/yyyy"
                        ejercicioId.isBlank() -> errorMensaje = "El ID de ejercicio es requerido"
                        ejercicioIdLong == null -> errorMensaje = "El ID de ejercicio debe ser un número válido"
                        ejercicioIdLong <= 0 -> errorMensaje = "El ID de ejercicio debe ser mayor a 0"
                        else -> {
                            try {
                                val fecha = parseDate(fechaTexto)
                                onConfirm(numeroSesionLong, fecha, ejercicioIdLong)
                                onDismiss()
                            } catch (e: Exception) {
                                errorMensaje = "Error al procesar la fecha"
                            }
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
fun EditarSesionDialog(
    sesion: Sesion,
    onDismiss: () -> Unit,
    onSave: (Sesion) -> Unit
) {
    var numeroSesion by remember { mutableStateOf(sesion.numeroSesion.toString()) }
    var fechaTexto by remember { mutableStateOf(formatDate(sesion.fecha)) }
    var ejercicioId by remember { mutableStateOf(sesion.ejercicioId.toString()) }
    var errorMensaje by remember { mutableStateOf("") }

    val numeroSesionLong = numeroSesion.trim().toLongOrNull()
    val ejercicioIdLong = ejercicioId.trim().toLongOrNull()
    
    val hasChanges = (numeroSesionLong != sesion.numeroSesion || 
                     fechaTexto.trim() != formatDate(sesion.fecha) || 
                     ejercicioIdLong != sesion.ejercicioId)
    val canSave = (numeroSesionLong != null && numeroSesionLong > 0 &&
                  fechaTexto.trim().isNotEmpty() && isValidDate(fechaTexto.trim()) &&
                  ejercicioIdLong != null && ejercicioIdLong > 0 &&
                  hasChanges)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Editar Sesión", style = MaterialTheme.typography.titleMedium) },
        text = {
            Column {
                OutlinedTextField(
                    value = numeroSesion,
                    onValueChange = { numeroSesion = it },
                    label = { Text("Número de Sesión") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    isError = numeroSesion.isBlank() && errorMensaje.isNotEmpty()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = fechaTexto,
                    onValueChange = { fechaTexto = it },
                    label = { Text("Fecha (dd/MM/yyyy)") },
                    placeholder = { Text("Ej: 11/12/2025") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    isError = fechaTexto.isBlank() && errorMensaje.isNotEmpty()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = ejercicioId,
                    onValueChange = { ejercicioId = it },
                    label = { Text("ID Ejercicio") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    isError = ejercicioId.isBlank() && errorMensaje.isNotEmpty()
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
                        numeroSesion.isBlank() -> errorMensaje = "El número de sesión es requerido"
                        numeroSesionLong == null -> errorMensaje = "El número de sesión debe ser un número válido"
                        numeroSesionLong <= 0 -> errorMensaje = "El número de sesión debe ser mayor a 0"
                        fechaTexto.isBlank() -> errorMensaje = "La fecha es requerida"
                        !isValidDate(fechaTexto.trim()) -> errorMensaje = "Formato de fecha inválido. Use dd/MM/yyyy"
                        ejercicioId.isBlank() -> errorMensaje = "El ID de ejercicio es requerido"
                        ejercicioIdLong == null -> errorMensaje = "El ID de ejercicio debe ser un número válido"
                        ejercicioIdLong <= 0 -> errorMensaje = "El ID de ejercicio debe ser mayor a 0"
                        !hasChanges -> errorMensaje = "No hay cambios para guardar"
                        else -> {
                            try {
                                val fecha = parseDate(fechaTexto.trim())
                                val sesionActualizada = sesion.copy(
                                    numeroSesion = numeroSesionLong,
                                    fecha = fecha,
                                    ejercicioId = ejercicioIdLong
                                )
                                onSave(sesionActualizada)
                                onDismiss()
                            } catch (e: Exception) {
                                errorMensaje = "Error al procesar la fecha"
                            }
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

private fun isValidDate(dateString: String): Boolean {
    return try {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        format.isLenient = false
        val date = format.parse(dateString)
        date != null
    } catch (e: Exception) {
        false
    }
}

private fun parseDate(dateString: String): Date {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    format.isLenient = false
    return format.parse(dateString) ?: throw IllegalArgumentException("Fecha inválida")
}

private fun formatDate(date: Date): String {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(date)
}