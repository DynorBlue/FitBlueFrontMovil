package org.utl.fitblueapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import org.utl.fitblueapp.ui.components.buttons.BotonAgregar
import org.utl.fitblueapp.ui.theme.azulFit
import org.utl.fitblueapp.ui.theme.blanco
import org.utl.fitblueapp.ui.theme.negro


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioSesionScreen() {

    var nombreUsuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    // Contenedor principal que ocupa toda la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el espacio disponible
            .background(color = negro)
            .padding(horizontal = 24.dp, vertical = 48.dp), // Espaciado de los bordes
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        // Sección del logo y título principal
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "FIT",
                fontSize = 55.sp, // Tamaño reducido para mejor armonía visual
                fontWeight = FontWeight.Light, // Fuente ligera para contraste
                color = blanco,
            )
            Text(
                text = "BLUE",
                fontSize = 70.sp,
                fontWeight = FontWeight.ExtraBold, // Fuente gruesa para destacar
                color = azulFit,
                modifier = Modifier.padding(start = 8.dp) // Espacio entre palabras
            )
        }

        Spacer(modifier = Modifier.height(70.dp)) // Separación entre logo y campos

        // Campos de entrada de texto para el usuario

        // Campo para ingresar el nombre de usuario
        OutlinedTextField(
            value = nombreUsuario,
            onValueChange = { nombreUsuario = it },
            label = {
                Text(
                    text = "Nombre de Usuario",
                    color = Color.Gray // Color gris para mejor contraste
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
shape = RoundedCornerShape(12.dp), // Esquinas redondeadas consistente con el otro campo para diseño moderno
            colors = TextFieldDefaults.colors(
                focusedContainerColor = blanco,
                unfocusedContainerColor = blanco,
                focusedTextColor = negro,
                unfocusedTextColor = negro,
                cursorColor = azulFit
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre campos

        // Campo para ingresar la contraseña
        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text(text = "Contraseña", color = Color.Gray) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
shape = RoundedCornerShape(12.dp), // Esquinas redondeadas
            colors = TextFieldDefaults.colors(
                focusedContainerColor = blanco,
                unfocusedContainerColor = blanco,
                focusedTextColor = negro,
                unfocusedTextColor = negro,
                cursorColor = azulFit
            ),
            visualTransformation = PasswordVisualTransformation(), // Oculta la contraseña con puntitos
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp)) // Espacio antes del botón principal

        // Botón para iniciar sesión
        BotonAgregar(
            texto = "INICIAR SESIÓN",
            onClick = { /* Lógica de inicio de sesión */ }
        )

        // Opción secundaria para recuperación de contraseña
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "¿Olvidaste tu contraseña?",
            color = Color.LightGray,
            fontSize = 14.sp,
            modifier = Modifier.padding(4.dp)
            // Se puede añadir Modifier.clickable para hacerla funcional
        )
    }
}

