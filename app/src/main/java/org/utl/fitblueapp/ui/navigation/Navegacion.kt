package org.utl.fitblueapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import org.utl.fitblueapp.db.AppDataBase
import org.utl.fitblueapp.db.repository.CategoriaRepositorio
import org.utl.fitblueapp.db.repository.EjercicioRepositorio
import org.utl.fitblueapp.db.viewModel.CategoriaViewModel
import org.utl.fitblueapp.db.viewModel.CategoriaViewModelFactory
import org.utl.fitblueapp.db.viewModel.EjercicioViewModel
import org.utl.fitblueapp.db.viewModel.EjercicioViewModelFactory
import org.utl.fitblueapp.ui.screens.CategoriaScreen
import org.utl.fitblueapp.ui.screens.EjercicioScreen

// Rutas de navegación
const val CATEGORIAS_ROUTE = "categorias"
const val EJERCICIOS_ROUTE = "ejercicios/{categoriaId}"
const val CATEGORIA_ID_ARG = "categoriaId"

@Composable
fun FitBlueNavigation(
    navController: NavHostController = rememberNavController(),
    database: AppDataBase
) {
    NavHost(
        navController = navController,
        startDestination = CATEGORIAS_ROUTE,
        modifier = Modifier
    ) {
        // Pantalla de Categorías
        composable(CATEGORIAS_ROUTE) {
            val categoriaViewModel: CategoriaViewModel = viewModel(
                factory = CategoriaViewModelFactory(CategoriaRepositorio(database.categoriaDao()))
            )
            
            CategoriaScreen(
                viewModel = categoriaViewModel,
                onCategoriaClick = { categoria ->
                    navController.navigate("ejercicios/${categoria.idCategoria}")
                }
            )
        }
        
        // Pantalla de Ejercicios (filtrados por categoría)
        composable(
            route = EJERCICIOS_ROUTE,
            arguments = listOf(
                navArgument(CATEGORIA_ID_ARG) {
                    type = NavType.LongType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val categoriaId = backStackEntry.arguments?.getLong(CATEGORIA_ID_ARG) ?: 1L
            
            val ejercicioViewModel: EjercicioViewModel = viewModel(
                factory = EjercicioViewModelFactory(EjercicioRepositorio(database.ejercicioDao()), categoriaId)
            )
            
            EjercicioScreen(
                viewModel = ejercicioViewModel,
                categoriaId = categoriaId
            )
        }
    }
}

// Funciones de extensión para navegación tipada
fun NavHostController.navigateToEjercicios(categoriaId: Long) {
    navigate("ejercicios/$categoriaId")
}

