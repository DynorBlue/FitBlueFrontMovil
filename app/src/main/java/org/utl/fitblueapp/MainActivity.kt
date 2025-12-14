package org.utl.fitblueapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.utl.fitblueapp.db.AppDataBase
import org.utl.fitblueapp.ui.navigation.FitBlueNavigation
import org.utl.fitblueapp.ui.theme.FitBlueAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Initialize database
        val database = AppDataBase.getDataBase(this)
        
        setContent {
            FitBlueAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FitBlueNavigation(database = database)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FitBlueAppTheme {
        // Preview would need mock data, keeping simple for now
    }
}